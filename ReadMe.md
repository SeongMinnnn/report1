## 과제 주의사항
1.Entity를 그대로 반환하지 말고 DTO에 담아서 반환
2.API를 이용해서 JSON 타입으로 반환
------------
## 서비스 요구사항
1)전체 게시글 목록 조회
- 제목, 작성자명, 작성 내용, 작성 날짜 조회
- 작성 날짜 기준 내림차순

2)게시글 작성 API (월)
- 제목, 작성자명, 비밀번호, 작성 내용 저장
- 저장된 게시글 Client로 반환

3)선택한 게시글 조회 API
- 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용 조회
- 선택한 게시글 수정 API (화)

4)수정 요청시 수정할 데이터와 비밀번호를 같이 보내서 일치여부 확인
- 제목, 작성자명, 작성 내용을 수정해서 Client로 반환

5)선택한 게시글 삭제 API (수)
- 삭제 요청시 비밀번호를 같이 보내서 비밀번호 일치여부 확인
- 삭제 후 성공 표시 반환
----------------
Use Case

![useCase.png](..%2F..%2FuseCase.png)
------------------------
## API 명세서

|기능|Method| url              | request                                                                                              |response|
|-----|--|------------------|------------------------------------------------------------------------------------------------------|---|
|작성|POST| /api/post        | {"username" : "username", "title" : "title",  "contents" : "contents", "password" : "password"}              |{"username" : "username", "title" : "title",  "contents" : "contents", "password" : "password"}|
|조회|GET| /api/search      |||
|선택조회|GET| /api/search/post |||
|수정|PUT| /api/post/{id} | {"username" : username2,    "title" : "title2",   "contents" : "contents2",   "password" : "password2"   } | {"username" : username,    "title" : "title",   "contents" : "contents2",   "password" : "password"   } |
|삭제|GET| /api/post/delete | {"password" : "password"} |삭제완료|
