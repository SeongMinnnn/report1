## 과제 주의사항
1.Entity를 그대로 반환하지 말고 DTO에 담아서 반환
2.API를 이용해서 JSON 타입으로 반환
------------
## 서비스 요구사항
- 전체 게시글 목록 조회 API
  + 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
  + 작성 날짜 기준 내림차순으로 정렬하기
- 게시글 작성 API
  + 제목, 작성자명(username), 비밀번호, 작성 내용을 저장하고 저장된 게시글을 Client 로 반환하기
  + 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
- 선택한 게시글 조회 API
  + 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
- 선택한 게시글 수정 API
  + 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
  + 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
- 선택한 게시글 삭제 API
  + 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
  + 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
  + 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
- 회원 가입 API
  + username, password를 Client에서 전달받기
  + username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(az), 숫자(09)로 구성되어야 한다.
  + password는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(az, AZ), 숫자(0~9)로 구성되어야 한다.
  + DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- 로그인 API
  + username, password를 Client에서 전달받기
  + DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
  + 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

![ERD](https://user-images.githubusercontent.com/122071252/220853279-0240dedd-83b2-463b-a9e9-f404dfa99e94.jpg)

------------------------
## API 명세서

|기능|Method| url              | request                                                                                              |response|
|-----|--|------------------|------------------------------------------------------------------------------------------------------|---|
|작성|POST| /api/post        | {"username": "user123", "title": "제목",  "contents": "내용", "password": "user!1234"}              |{"username": "username", "title": "title",  "contents": "contents", "password": "user!1234"}|
|로그인|POST|/api/user/login||{"username":"user123", "password": "user!1234"}
|조회|GET| /api/posts      ||{"username": "user123", "contents": "내용", "title": "제목", "createdAt" "2022-07-25T12:43:01.226062", "modifiedAt": "2022-07-25T12:43:01.22602"}|
|선택조회|GET| /api/post ||{"username": "user123", "contents": "내용", "title": "제목", "createdAt" "2022-07-25T12:43:01.226062", "modifiedAt": "2022-07-25T12:43:01.22602", "comments" : "댓글"}|
|수정|PUT| /api/post/{id} | {"username": user123,    "title" : "제목",   "contents" : "내용",   "password" : "user!1234"   } | {"username" : user123,    "title" : "제목2",   "contents" : "내용2",   "password" : "user!1234"   } |
|삭제|GET| /api/post/{id} | {"password" : "password"} |{"success":true}|
