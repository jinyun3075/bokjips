# bokjips Server REPO

### 임시 서버 [52.79.165.66:8081](http://52.79.165.66:8081/)
![image](https://user-images.githubusercontent.com/64072136/172780096-533aa82b-a65f-423a-9761-d00941657edf.png)

## API 명세 목차
- ### [1 회사](#회사)
  - [1.1 회사 등록](#회사-등록)
  - [1.2 회사 자세히 보기](#회사-자세히-보기)
  - [1.3 회사 리스트](#회사-리스트)
  - [1.4 회사 수정](#회사-수정)
  - [1.5 회사 삭제](#회사-삭제)
  - [1.6 회사 미니 리스트](#회사-미니-리스트)
  - [1.7 좋아요 기능](#좋아요-기능)

- ### [2 유저](#유저)
  - [2.1 회원가입](#회원가입)
  - [2.2 로그인](#로그인)

- ### [3 댓글](#댓글)
  - [3.1 댓글 등록](#댓글-등록)
  - [3.2 댓글 리스트](#댓글-리스트)
  - [3.3 댓글 삭제](#댓글-삭제)

## 회사
### 회사 등록
- api
  - /corp/insert (post)

- headers
  - Content-type : "application/json"

- req
  - name: String
  - site: String
  - career: String
  - category: Array<String>
  - stock: Boolean
  - image: String
  - welfareList: Object
    - {title}
      - subTitle: String
      - options: String
   
- res
  - corp_id: String
  - name: String
  - site: String
  - career: String
  - category: Array<String>
  - stock: Boolean
  - good: Number
  - goodStatus: Boolean
  - image: String
  - welfareList: Object
    - {title}
      - subTitle: String
      - options: String
  - regDate: Date
  - modDate: Date
 
- fail
  - name 중복
    
### 회사 자세히 보기

- api
  - /corp/select/:corp_id/:user_id (get)

- headers
  - Content-type : "application/json"
   
- res
  - corp_id: String
  - name: String
  - site: String
  - career: String
  - category: Array<String>
  - stock: Boolean
  - good: Number
  - goodStatus: Boolean
  - image: String
  - welfareList: Array
    - {title}
      - subTitle: String
      - options: String
  - regDate: Date
  - modDate: Date

### 회사 리스트

- api
  - /corp/select(get)
  - /corp/select?page=Number&size=Number(get)

- headers
  - Content-type : "application/json"

- res
 - dtoList:Array
    - corp_id: String
    - name: String
    - site: String
    - career: String
    - category: Array<String>
    - stock: Boolean
    - good: Number
    - image: String
    - welfareList: Array<String>
    - regDate: Date
    - modDate: Date   
  - totalPage: Number
  - page: Number
  - size: Number
  - prev: boolean
  - next: boolean
  - start: Number
  - end: Number
  - pageList: Array<String>

### 회사 수정

- api
  - /corp/update/:corp_id (put)

- headers
  - Content-type : "application/json"

- req
  - name: String
  - site: String
  - career: String
  - category: Array<String>
  - stock: Boolean
  - image: String
  - welfareList: Object
    - {title}
      - subTitle: String
      - options: String
     
- res
  - corp_id: String
  - name: String
  - site: String
  - career: String
  - category: Array<String>
  - stock: Boolean
  - good: Number
  - goodStatus: Boolean
  - image: String
  - welfareList: Array
    - {title}
      - subTitle: String
      - options: String
  - regDate: Date
  - modDate: Date
- fail
  - name 중복


### 회사 삭제

- api
  - /corp/delete/:corp_id (delete)

- headers
  - Content-type : "application/json"
   
- res
  - "삭제 완료"

- fail
  - 없는 corp_id일 경우


### 회사 미니 리스트

- api
  - /corp/select/mini (post)

- headers
  - Content-type : "application/json"

- req
  - corp_id: String
  - category: Array<String>
  - stock: boolean
  
- res
  - Array
    - name: String
    - corp_id : String
    - good: Number

### 좋아요 기능

- api
  - /corp/good (post)

- headers
  - Content-type : "application/json"

- req
  - corp_id: String
  - user_id: String
  
- res
  - "좋아요 등록, 취소"
  
## 유저
### 회원가입
- api
  - /user/insert (post)
 
- headers
  - Content-type : "application/json"

- req
  - email: String
  - name: String
  - password: String

- res
  - user_id: String
  - email: String
  - name: String
  - token: null

- fail
  - email 형식이 아닐 때
  - 중복 email, name 일 때

### 로그인

- api
  - /user/login (post)
 
- headers
  - Content-type : "application/json"

- res
  - user_id: String
  - email: String
  - name: String
  - token: String

- fail
  - email 형식이 아닐 때
  - 중복 email, name 일 때

## 댓글

### 댓글 등록

- api
  - /comments/insert (post)
 
- headers
  - Content-type : "application/json"

- req
  - title: String
  - content: String
  - user_id: String
  - corp_id: String

- res
  - comments_id: String
  - title: String
  - content: String
  - writer: String
  - regdate: Date
  - modDate: Date

### 댓글 리스트

- api
  - /comments/select/:corp_id (get)
  - /comments/select/:corp_id?page=1&size=10 (get)
 
- headers
  - Content-type : "application/json"

- res
   - dtoList:Array
      - comments_id: String
      - title: String
      - content: String
      - writer: String
      - regdate: Date
      - modDate: Date
  - totalPage: Number
  - page: Number
  - size: Number
  - prev: boolean
  - next: boolean
  - start: Number
  - end: Number
  - pageList: Array<String>
  
  
### 댓글 삭제
  
- api
  - /comments/delete/:comments_id (delete)

- headers
  - Content-type : "application/json"
  
- res
  - "삭제 완로"

- fail
  - 없는 id일 경우

 
