피로니어 3기 세번째 과제
---
나만의 컨벤션 만들기 & SQL Level 3 이상 10문제
## 컨벤션
### 디렉토리 구조
```angular2html
🗂️pironeer3rd.SpringbootBoard
│  SpringbootBoardApplication.java
│
├─📂board
│  ├─📂controller
│  │      BoardController.java
│  │
│  ├─📂dto
│  │  ├─📂request
│  │  │      BoardCreateReq.java
│  │  │      BoardUpdateReq.java
│  │  │
│  │  └─📂response
│  │          BoardResponse.java
│  │
│  ├─📂entity
│  │      Board.java
│  │
│  ├─📂mapper
│  │      BoardMapper.java
│  │
│  ├─📂repository
│  │      BoardMemoryRepository.java
│  │      BoardRepository.java
│  │
│  └─📂service
│          BoardService.java
│
├─📂global
│  ├─📂config
│  │      OpenApiConfig.java
│  │      WebConfig.java
│  │
│  ├─📂dto
│  │  └─📂response
│  │      │  ErrorResponse.java
│  │      │  JwtTokenSet.java
│  │      │  SuccessResponse.java
│  │      │
│  │      └─📂result
│  │              ExceptionResult.java
│  │              ListResult.java
│  │              ResponseState.java
│  │              SingleResult.java
│  │
│  ├─📂exception
│  │      CustomException.java
│  │      ErrorCode.java
│  │      ExceptionAdvice.java
│  │
│  ├─📂jwt
│  │      JwtInterceptor.java
│  │      JwtUtil.java
│  │
│  └─📂service
│          AuthService.java
│          ResponseService.java
│
└─📂member
    ├─📂controller
    │      MemberController.java
    │
    ├─📂dto
    │  ├─📂request
    │  │      MemberCreateReq.java
    │  │      MemberLoginReq.java
    │  │
    │  └─📂response
    │         MemberResponse.java
    ├─📂entity
    │      Member.java
    │
    ├─📂mapper
    │      MemberMapper.java
    │
    ├─📂repository
    │      MemberRepository.java
    │
    └─📂service
            MemberService.java
```
### 이름 (Naming)
- 모든 패키지 이름은 소문자로 구성하였다.
- 클래스/인터페이스 이름에 대문자 카멜표기법을 적용하였다.
- 메서드 이름에는 소문자 카멜표기법 적용하였다.
### 선언 (Declarations)
- 소스파일당 1개의 탑레벨 클래스를 담았다.
- 한 줄에 한 문장만 담았다.
### 디렉토리 구조
- `member` 도메인과 `board` 도메인의 구조를 맞추기 위해 `member` 도메인에, `mapper` 디렉토리와 `dto.response` 디렉토리를 추가했다.