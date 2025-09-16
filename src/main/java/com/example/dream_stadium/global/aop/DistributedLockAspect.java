package com.example.dream_stadium.global.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        String lockKey = distributedLock.key();
        long waitTime = distributedLock.waitTime();
        long leaseTime = distributedLock.leaseTime();

        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS)) {
                return joinPoint.proceed();
            } else {
                throw new RuntimeException("Lock acquisition failed for key: " + lockKey);
            }
        } catch (InterruptedException e) { //InterruptedException 은 실행중에 다른 스레드에 의해 중단되는 경우니까 체크예외이다.(컴파일러가 처리여부 강제한다.)
            throw new RuntimeException(e);
        } catch (Throwable e) { // 예외처리니까 언체크예외이다.(런타임에서 발생)
            throw new RuntimeException(e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

/*
java.lang.Object
  └─ java.lang.Throwable
       ├─ java.lang.Error (언체크)
       └─ java.lang.Exception
             ├─ java.lang.RuntimeException (언체크)
             └─ ... (checked exceptions like IOException, InterruptedException) (체크)
*/
