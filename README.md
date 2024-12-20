# 목차

1. [프로젝트 목적](#프로젝트-목적)
1. [프로젝트 소개](#프로젝트-소개)
2. [사용 기술](#사용-기술)
3. [주요 기능](#주요-기능)
4. [API 명세](#api-명세)
<br></br>

# 프로젝트 목적
MCV 패턴, mybatis, crud 학습
<br></br>
# 프로젝트 소개

스프링을 배우고 스프링으로 혼자 간단히 만들 수 있는 게시판을 직접 만들어보고 배우기 위해 제작하였습니다.

<br></br>

 ## 테이블 스키마
 <img src= "https://github.com/user-attachments/assets/f41ca2b9-8290-4518-9750-77b15b1c1596" alt="DB" width="100%" height="auto"> 
<br></br>

# 사용 기술

## 3-1 백엔드

### 주요 프레임워크 / 라이브러리
- **Java**
- **SpringBoot**
- **Mybatis**
- **Thymeleaf**

### Build Tool
- **Gradle**
  
### DataBase
- **MySQL**

## 3-2 프론트엔드
- **HTML**
- **JavaScript**

<br></br>
# 주요 기능

- **회원 가입/로그인**: 
  - client에서 id, password, nickname 입력
 
  - **회원가입**
  - id 중목 검사
 
  - **로그인**
  - id, password DB 조회
  - 로그인 성공 시 세션에 저장

- **게시글**: 
  - 로그인 상태일 시 게시글 작성 가능
  - 작성자의 경우 게시글 수정 및 삭제 가능
  - 조회수 기능 구현
  - 댓글 기능 구현
  - 제목, 내용으로 게시글 검색 기능 구현

- **좋아요**: 
  - 로그인 상태일 시 게시글에 좋아요 기능 가능
  - 자신의 작성한 게시물과 한번 좋아요한 게시글에는 좋아요 불가능
 
- **마이페이지**: 
  - 로그인 상태일 시 자신이 작성한 글, 좋아요한 글, 댓글을 남긴 글 조회 가능


#  API 명세

## Member API

| **기능**              | **Method** | **URL**                | **파라미터**                                                                                                                                   | **응답 형식**                | **설명**                                                                 |
|-----------------------|------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|------------------------------|--------------------------------------------------------------------------|
| **회원가입 페이지 요청** | GET        | `/member/join`         | 없음                                                                                                                                          | HTML 페이지                  | 회원가입 폼 페이지를 반환합니다.                                         |
| **아이디 중복 확인**    | GET        | `/member/check-id`     | `id` (String, 필수): 중복 확인할 사용자 아이디.                                                                                               | JSON                         | 중복 여부를 Boolean 값으로 반환. `true`: 사용 가능, `false`: 중복.        |
| **회원가입 요청**       | POST       | `/member/join`         | Body: `{ "id": "string", "password": "string", "nickname": "string" }`                                                                       | Redirect (`/`)               | 회원가입 성공 시 메인 페이지로 리다이렉션.                               |
| **로그인 페이지 요청**  | GET        | `/member/login`        | 없음                                                                                                                                          | HTML 페이지                  | 로그인 폼 페이지를 반환합니다.                                           |
| **로그인 요청**         | GET        | `/member/accessLogin`  | `id` (String, 필수): 사용자 아이디. <br>`password` (String, 필수): 사용자 비밀번호.                                                          | JSON                         | 로그인 성공 여부를 Boolean 값으로 반환. `true`: 성공, `false`: 실패.      |
| **로그인 상태 확인**    | GET        | `/member/isLogin`      | 없음                                                                                                                                          | JSON                         | 현재 로그인 상태와 사용자 ID를 반환.                                      |
| **로그아웃 요청**       | GET        | `/member/logout`       | 없음                                                                                                                                          | Redirect (`/`)               | 로그아웃 후 메인 페이지로 리다이렉션.                                    |

<br></br>

## Board API

| **기능**               | **Method** | **URL**                        | **파라미터**                                                                 | **응답 형식**     |
|------------------------|------------|--------------------------------|------------------------------------------------------------------------------|-------------------|
| **홈 페이지 요청**      | GET        | `/`                            | 없음                                                                         | HTML 페이지       |
| **게시글 작성 페이지 요청** | GET        | `/board/write`                | 없음                                                                         | HTML 페이지       |
| **게시글 작성 요청**     | POST       | `/board/write`                | Body: `{ "boardTitle": "string", "boardContents": "string" }`                | Redirect (`/`)     |
| **게시글 상세 페이지 요청** | GET        | `/board/{boardId}`            | `boardId` (int, 필수): 게시글 ID                                              | HTML 페이지       |
| **게시글 수정 페이지 요청** | GET        | `/board/modify/{boardId}`     | `boardId` (int, 필수): 게시글 ID                                              | HTML 페이지       |
| **게시글 수정 요청**     | POST       | `/board/modify/{boardId}`     | Body: `{ "boardId": "int", "boardTitle": "string", "boardContents": "string" }` | Redirect (`/board/{boardId}`) |
| **게시글 좋아요 요청**   | GET        | `/board/like`                 | `boardId` (int, 필수): 게시글 ID<br>`userId` (String, 필수): 사용자 ID       | JSON              |
| **게시글 삭제 요청**     | GET        | `/board/remove`               | `boardId` (int, 필수): 게시글 ID                                              | 없음              |
| **댓글 작성 요청**       | POST       | `/board/commentWrite`         | Body: `{ "commentBoardId": "int", "commentContents": "string" }`             | Redirect (`/board/{commentBoardId}`) |
| **마이 페이지 요청**       | GET        | `/board/myPage`               | 없음                                                                         | HTML 페이지       |
| **내가 쓴 게시글 조회**   | GET        | `/board/myWrite`              | 없음                                                                         | HTML 페이지       |
| **내가 작성한 댓글 조회** | GET        | `/board/myComment`            | 없음                                                                         | HTML 페이지       |
| **내가 좋아요한 게시글 조회** | GET        | `/board/myLike`               | 없음                                                                         | HTML 페이지       |
| **게시글 검색 요청**           | POST       | `/board/search`                    | `keyword` (String, 필수): 검색 키워드                                         | HTML 페이지           |
