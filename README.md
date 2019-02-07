# searchSomething

카카오 검색 API 를 이용해 키워드 장소 검색 서비스 개발 / 로그인기반 / 장소 상세 조회/ 인기 검색어 목록 

> * 포트는 8081 을 사용합니다. (application.properties 에서 수정 가능.)

> * H2 DB는 memory 방식으로 되어있어 서버를 재시작하면 데이타가 초기화 됩니다. 

> * 로그인 : user / 비밀번호 : user(암호화 상태)


### 서비스 스펙
1. Java 8 버전을 사용
1. framework - spring-boot
1. server - Spring-boot 에서 제공하는 내부서버(Tomcat) 사용 
1. Database - h2 (maven dependency)

### Back-end 추가 모듈(라이브러리)
1. httpclient - for RestAPI connected
1. JPA
1. Security
1. lombok

### Front-end 추가 모듈(라이브러리)
1. jquery-3.2.1 - Front-end framework.
1. Bootstrap 4-beta - 화면 구성을 위해 CSS만 이용
1. bootpag - jQuery plugin for dynamic pagination


### 설치

```
$ git clone https://github.com/flwkemd/solveEx.git
$ cd solveEx

```

### 실행(로컬)

```
$ mvn clean compile
$ mvn spring-boot:run
$ curl -v localhost:8081
```


### 배포용 (Jar 압축)

```
$ mvn clean compile
$ mvn pakage
$ cd target
```


문의: <flwkemd@naver.com>
