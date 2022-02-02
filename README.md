<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-6.14.15-blue">
  <img alt="node" src="https://img.shields.io/badge/node-14.18.2-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
# 요구사항

---

- [x]  단위 테스트 코드 작성
- [x]  비지니스 로직 리팩토링

# 요구사항 설명

---

## 단위 테스트 코드 작성하기

- 아래 두 테스트 클래스의 테스트 메서드를 완성해야 함.
    - LineServiceMockTest
    - LineServiceTest
- 기존 기능에 대한 테스트 작성이기 때문에 테스트 작성 시 바로 테스트가 성공해야 함.

## 비지니스 로직 리팩토링

- 구간 추가 / 삭제 기능에 대한 비지니스 로직은 현재 LineService에 대부분 위치함.
  도메인 클래스(Line)로 옮기기
- 리팩토링 시 LineTest의 테스트 메서드를 활용하여 TDD 사이클을 진행
- 리팩토링 과정에서 Line 이외 추가적인 클래스가 생겨도 좋음
    - 구간 관리에 대한 책임을 Line 외 별도의 도메인 객체가 가지게할 수 있음.
  
# 🚀 1단계 - 구간 추가 기능 변경
# 요구사항

---

- [x]  변경된 스펙에 대한 인수 조건 도출 및 인수 테스트 작성
- [x]  구간 추가 기능 요구사항 변경 대응
  → 저장 시에는 구간 순서를 구분하지 않고 조회 시에만 구분한다.
  저장할 경우 우선 순위 구분 컬럼이 필요한데 이렇게 되면 100개 구간이면 99개 구간의 컬럼을 update 쳐야하는 상황이 있어서
  리뷰어 님과 상의 결과 해당 미션에서는 `저장 시 구분없이 조회 시에 구분하는 것으로 하면 된다고 한다.`
- [x]  비지니스 로직 TDD로 기능 구현

# 요구사항 설명

---

## 변경된 스펙 - 구간 추가 제약사항 변경

### ~~📌 역 사이에 새로운 역을 등록할 경우~~

간선 허용 불가함.

- 기존 구간의 역을 기준으로 새로운 구간을 추가한다.
  - 기존 구간 A-C에 신규 구간 A-B를 추가하는 경우 A역을 기준으로 추가
  - 결과로 A-B, B-C 구간이 생김
- 새로운 길이를 뺀 나머지를 새롭가 추가된 역과의 길이로 설정

### ~~📌 새로운 역을 상행 종점으로 등록할 경우~~

### ~~📌 (기존 기능)새로운 역을 하행 종점으로 등록할 경우~~

### ~~📌 노선 조회 시 응답되는 역 목록 수정~~

- 구간이 저장되는 순서로 역 목록을 조회할 경우 순서가 다르게 조회될 수 있음.
- 아래의 순서대로 역 목록을 응답하는 로직을 변경해야 함.
  1. 상행 종점이 상행역인 구간을 먼저 찾는다.
  2. 해당 구간의 하행역이 상행역인 다른 구간을 찾는다.
  3. 2번을 반복하다가 하행 종점역을 찾으면 조회를 멈춘다.

## 변경된 스펙 - 예외 케이스

### ~~📌 역 사이에 새로운 역을 등록할 경우 기존 역 사이 길이 보다 크거나 같은 수 없음~~

### ~~📌 상행역과 하행역이 이미 노선에 모두 등록되어 있다면 추가할 수 없음.~~

### ~~📌 상행역과 하행역 둘 중 하나도 등록되어 있지 않으면 추가할 수 없음.~~
