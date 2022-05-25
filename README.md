# bokjips Server REPO

### 임시 서버 [52.79.165.66:8081](http://52.79.165.66:8081/)
![image](https://user-images.githubusercontent.com/64072136/168822378-207b2a0d-5507-4bd5-ad74-79c62924d801.png)

## API 명세 목차
- ### [1 회사](#회사)
  - [1.1 회사 등록](#회사-등록)
  - [1.2 회사 자세히 보기](#회사-자세히-보기)
  - [1.3 회사 리스트](#회사-리스트)
  - [1.4 회사 수정](#회사-수정)
  - [1.5 회사 삭제](#회사-삭제)


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
    - category: Array
      - String
    - stock: Boolean
    - image: String
    - welfareList: Array
      - title: String
      - subTitle: String
      - options: String
   
- res
    - corp_id: String
    - name: String
    - site: String
    - career: String
    - category: Array
      - String
    - stock: Boolean
    - good: Number
    - image: String
    - welfareList: Array
      - {title}: Array
        - subTitle: String
        - options: String
    - regDate: Date
    - modDate: Date
 
- fail
    - name 중복
    
### 회사 자세히 보기

- api
    - /corp/select/:corp_id (get)

- headers
    - Content-type : "application/json"
   
- res
    - corp_id: String
    - name: String
    - site: String
    - career: String
    - category: Array
      - String
    - stock: Boolean
    - good: Number
    - image: String
    - welfareList: Array
      - {title}: Array
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
      - category: Array
         - String
      - stock: Boolean
      - good: Number
      - image: String
      - welfareList: Array
         - {title}: Array
            - subTitle: String
            - options: String
      - regDate: Date
      - modDate: Date   
  - totalPage: Number
  - page: Number
  - size: Number
  - prev: boolean
  - next: boolean
  - start: Number
  - end: Number
  - pageList: Array

### 회사 수정

- api
    - /corp/update/:corp_id (put)

- headers
    - Content-type : "application/json"

- req
    - name: String
    - site: String
    - career: String
    - category: Array
      - String
    - stock: Boolean
    - image: String
    - welfareList: Array
      - title: String
      - subTitle: String
      - options: String
     
- res
    - corp_id: String
    - name: String
    - site: String
    - career: String
    - category: Array
      - String
    - stock: Boolean
    - good: Number
    - image: String
    - welfareList: Array
      - {title}: Array
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
