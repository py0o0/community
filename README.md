# 목차


1. [프로젝트 목적](#프로젝트-목적)
1. [프로젝트 소개](#프로젝트-소개)
2. [기술 스택](#기술-스택)
3. [주요 기능](#주요-기능)
4. [API 명세](#api-명세)
5. [Snapshot](#snapshot)
<br></br>

# 프로젝트 목적
MCV 패턴, mybatis, crud 학습
<br></br>
# 프로젝트 소개

스프링을 배우고 스프링으로 혼자 간단히 만들 수 있는 게시판을 직접 만들어보고 배우기 위해 제작하였습니다.

<br></br>

 ## Use Case Diagram:
<img src="https://github.com/user-attachments/assets/81dec1a2-777d-4826-b384-343f67a4da01" alt="UCD" width="100%" height="auto"> 
<br></br>

 ## Communication Diagram:
<img src= "https://github.com/user-attachments/assets/1ee8e171-394c-4416-83a8-4dff7156bacd" alt="CD" width="100%" height="auto"> 
<br></br>

 ## 테이블 스키마:
 <img src= "https://github.com/user-attachments/assets/8a7d1278-1df2-417a-a179-80efa6994cc2" alt="DB" width="100%" height="auto"> 
<br></br>

# 기술 스택
<img src= "https://github.com/user-attachments/assets/48e33715-5828-450a-90dd-caf96ea346b4" alt="기술스택" width="100%" height="auto"> 
<br></br>

# 주요 기능
 <br></br>
- **게시판**: 
  - 좋아요 기능, 조회수 기능, CRUD 기능

<br></br>
- **사용자**: 
  - 마이페이지 기능, Session을 이용한 로그인 기능, 회원가입 기능

<br></br>
- **댓글**: 
  - CRUD 기능

 <br></br>

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

| **기능**               | **Method** | **URL**                        | **파라미터**                                                                 | **응답 형식**     | **설명**                                                                 |
|------------------------|------------|--------------------------------|------------------------------------------------------------------------------|-------------------|--------------------------------------------------------------------------|
| **홈 페이지 요청**      | GET        | `/`                            | 없음                                                                         | HTML 페이지       | 게시글 리스트를 출력하는 홈 페이지를 반환합니다.                         |
| **게시글 작성 페이지 요청** | GET        | `/board/write`                | 없음                                                                         | HTML 페이지       | 게시글 작성 폼 페이지를 반환합니다.                                       |
| **게시글 작성 요청**     | POST       | `/board/write`                | Body: `{ "boardTitle": "string", "boardContents": "string" }`                | Redirect (`/`)     | 게시글을 작성 후 홈 페이지로 리다이렉션합니다.                            |
| **게시글 상세 페이지 요청** | GET        | `/board/{boardId}`            | `boardId` (int, 필수): 게시글 ID                                              | HTML 페이지       | 게시글 상세 내용과 댓글을 표시하는 페이지를 반환합니다.                  |
| **게시글 수정 페이지 요청** | GET        | `/board/modify/{boardId}`     | `boardId` (int, 필수): 게시글 ID                                              | HTML 페이지       | 게시글 수정 폼 페이지를 반환합니다.                                       |
| **게시글 수정 요청**     | POST       | `/board/modify/{boardId}`     | Body: `{ "boardId": "int", "boardTitle": "string", "boardContents": "string" }` | Redirect (`/board/{boardId}`) | 수정된 게시글을 저장하고 해당 게시글 페이지로 리다이렉션합니다.       |
| **게시글 좋아요 요청**   | GET        | `/board/like`                 | `boardId` (int, 필수): 게시글 ID<br>`userId` (String, 필수): 사용자 ID       | JSON              | 게시글 좋아요 여부를 반환하고 좋아요 수를 업데이트합니다.                 |
| **게시글 삭제 요청**     | GET        | `/board/remove`               | `boardId` (int, 필수): 게시글 ID                                              | 없음              | 게시글을 삭제합니다.                                                      |
| **댓글 작성 요청**       | POST       | `/board/commentWrite`         | Body: `{ "commentBoardId": "int", "commentContents": "string" }`             | Redirect (`/board/{commentBoardId}`) | 댓글을 작성하고 해당 게시글 페이지로 리다이렉션합니다.           |
| **내 페이지 요청**       | GET        | `/board/myPage`               | 없음                                                                         | HTML 페이지       | 사용자의 내 페이지를 반환합니다.                                          |
| **내가 쓴 게시글 조회**   | GET        | `/board/myWrite`              | 없음                                                                         | HTML 페이지       | 사용자가 작성한 게시글 리스트를 반환합니다.                             |
| **내가 작성한 댓글 조회** | GET        | `/board/myComment`            | 없음                                                                         | HTML 페이지       | 사용자가 작성한 댓글이 달린 게시글 리스트를 반환합니다.                 |
| **내가 좋아요한 게시글 조회** | GET        | `/board/myLike`               | 없음                                                                         | HTML 페이지       | 사용자가 좋아요를 누른 게시글 리스트를 반환합니다.                      |
