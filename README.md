# dream_stadium

# 고객이 경기장 예매에 불편함을 겪지 않도록 만든 개인 프로젝트

## ◽ 프로젝트 정보
- 고객이 경기장 예매에 불편함을 겪지 않도록 만든 프로젝트 입니다. 개인 프로젝트에서는 팀 프로젝트와 달리 실제 환경에 가까운 조건을 만들기 위해 DBeaver, JMeter, JWT, Spring Security를 적용하여 여러 사용자가 동시에 요청을 보내는 상황을 시뮬레이션했고, 성공적으로 테스트를 마쳤습니다.
- 또한 Docker Compose, EC2, GitHub Actions를 활용해 CI/CD 환경을 구축하며 배포 자동화까지 구현했습니다.

## 🚀 주요 기능
- 좌석 예매
- spring security를 이용한 로그인
- 간단한 알람 기능
- 쿠폰기능
- docker-compose 적용 및 CI/CD 파이프라인 구현
- 부하 테스트

## 🔧 사용 기술
<p align="center">
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white"> 
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/SpringMVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/Redisson-FF0000?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/DockerCompose-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/GitHubActions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/JMeter-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white">
</p>

## ⏰ 프로젝트 기간
2025.08.11 ~ 2025. 09. 03 (약 3주 +a)

## 🧑‍💻 팀원 소개
| 이름       | 역할   | 담당 업무                  |
|-----------|--------|-------------------------|
| 김현준      |  팀장 | 전체기능| 


## 🔗 와이어프레임
[와이어 프레임 링크] https://www.figma.com/design/XfzXpl6Q6GOpBJrvhrFGiW/Untitled?node-id=0-1&p=f&m=draw
## 💻 ERD
```mermaid
erDiagram

user{
bigint user_id PK "user_id"
varchar name "name"
varchar password "password"
}

shopping_mall {
BIGINT shopping_mall_id PK
INT business_info_rating
MEDIUMTEXT business_name
MEDIUMTEXT business_status
MEDIUMTEXT business_type
MEDIUMTEXT certification_marks
MEDIUMTEXT company_address
MEDIUMTEXT customer_complaint_board
MEDIUMTEXT domain_name
VARCHAR(255) e_commerce_license
MEDIUMTEXT estimated_delivery_display
MEDIUMTEXT extra_personal_info_request
DATE  first_report_date
MEDIUMTEXT main_products
MEDIUMTEXT membership_cancellation
DATE monitoring_date
MEDIUMTEXT operator_email
INT overall_rating
INT payment_method_rating
MEDIUMTEXT payment_methods
MEDIUMTEXT phone_number
INT privacy_security_rating
MEDIUMTEXT security_server
MEDIUMTEXT privacy_policy
MEDIUMTEXT purchase_safety_service
MEDIUMTEXT required_homepage_info
VARCHAR(255) site_establishment_year
MEDIUMTEXT store_name
MEDIUMTEXT terms_compliance
INT terms_of_service_rating
INT withdrawal_policy_rating
MEDIUMTEXT withdrawal_possible
MEDIUMTEXT withdrawal_shipping_fee
}
```
## 📑 API 명세서
### 1. 회원 관련 API 
  - /auth<dr>
#### AuthController
| 기능       | Method   | URL              | Request | Response |
|-----------|----------|------------------|---------|--------|
| 회원 가입    | `POST`  | /auth/sign-up    | Body   | 201 Create | 
| 로그인      | `POST`  | /auth/log-in      | Body    | 200 OK |


### 2. 쇼핑몰 관련 API
- /shopping-mall<dr>
#### ShoppingMallController
| 기능       | Method   | URL              | Request | Response |
|-----------|----------|------------------|---------|--------|
| 전체 평점 및 업체 상태 리스트 조회   | `GET`  | /shopping-mall/status-ratings   | param   | 200 OK | 
| 페이지네이션 적용 리스트 조회 | `GET`  | /shopping-mall/status-ratings/page/{page}     | param    | 200 OK | 
| 커서 기반 페이지네이션 적용 리스트 조회| `GET`  | /shopping-mall/status-ratings/cursor-page   | param    | 200 OK |
| CSV 파일 업로드 및 데이터 저장    | `POST`  | /shopping-mall/collection    |     | 200 OK | 

---
## 📜 트러블 슈팅
- [CSV파일 한글 깨짐 현상](https://wax-drop-ff7.notion.site/CSV-193f00cdce4c80cc810fee84e97d2b42)
- [‘MysqlDataTruncation’ 오류 해결 과정](https://wax-drop-ff7.notion.site/MysqlDataTruncation-193f00cdce4c8061a26ad1c43691c5d4)
