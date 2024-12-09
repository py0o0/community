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

- **로그인 기능**: 
  - 사용자가 입력한 아이디와 비밀번호를 서버로 전송하여, 서버에서 데이터베이스에 저장된 사용자 정보와 일치하는지 여부를 검사합니다.
  - **동적 흐름**:
 
    
    1. 사용자가 입력한 ID와 비밀번호를 GET 요청을 통해 서버로 전송함니다.
 
    
    2. 서버는 데이터베이스에서 해당 아이디와 비밀 번호를 조회한뒤 결과를 반환합니다.
  
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
| **Method** | **Path**                       | **Query Parameters**              | **Body**  | **Return Type**               | **Description**                              |
|------------|--------------------------------|-----------------------------------|-----------|-------------------------------|----------------------------------------------|
| GET        | `/api/users/preferences`       | `id` (String)                     | -         | `List<User>`                  | 사용자 ID로 사용자 선호 정보를 반환합니다. |
| GET        | `/api/users/existsId`          | `id` (String)                     | -         | `Boolean`                     | 사용자 ID의 존재 여부를 반환합니다.        |
| GET        | `/api/users/Login`             | `id` (String), `password` (String) | -         | `Boolean`                     | 사용자 ID와 비밀번호로 로그인 성공 여부를 반환합니다. |
| POST       | `/api/users/create`            | -                                 | `User`    | `User`                        | 새 사용자를 생성합니다.                    |
| GET        | `/api/foodsRandomByCategory`   | `cate1` (String), `cate2` (String) | -         | `Food`                        | 음식 카테고리로 랜덤 음식을 반환합니다.    |

<br></br>
