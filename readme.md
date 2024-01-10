# 출석체크 및 TIL 전시 사이트

## 1. 목적과 배경

이 문서는 간단한 출석체크 및 Today I Learned(TIL) 전시 사이트를 개발하기 위한 기획 문서입니다. 이 사이트는 사용자들이 매일 출석체크를 하고, 배운 것을 기록하며 공유할 수 있는 공간을 제공합니다.

## 2. 타겟 사용자

- 개발자 본인(추후 확대 예정)
- 회원으로 등록이 된 사람

## 3. 주요 기능

### 출석체크 기능

- 사용자는 매일 사이트에 로그인하여 출근/ 퇴근 버튼을 누름으로써 log를 남길 수 있습니다.
- 해당 로그는 각 날짜의 달력 위에 표시됩니다.

### TIL 전시 기능

- 사용자는 달력의 각 칸에 자신의 TIL 링크를 달아 놓을 수 있습니다.
- 달력 칸의 위 숫자 부분을 클릭함으로써 링크를 설정하거나 재 설정할 수 있습니다.
- 달력 칸의 아래 숫자 부분을 클릭함으로써 해당 링크로 이동할 수가 있음

## 4. 사이트 구조 및 디자인

https://www.figma.com/file/Y5CG0cemiurnP4P4QL7pqF/attendance%26TILBoard?type=design&node-id=0-1&mode=design&t=3tx4yo4K0EHPP5iT-0

## 5. 기술 스택

- **Frontend**: html, css, js
- api: graphql
- **Backend**: spring(gradle)
- **Database**: MySQL

## 6. 보안 계획

- firebase 토큰을 통한 사용자 인증

## 7. 개발 일정 및 예산

- 프로젝트 시작일: 2024/01/04
- 주요 Milestones
    - database 설계
    - spring 서버 설계
    - 프론트 개발
- 예산 계획
    - 없음

## 8. 테스트 및 배포 계획

- 단위 테스트, 통합 테스트 계획
    - 각 api별 단위 테스트
- 배포 전략 및 플랫폼

## 9. 유지 및 관리

- 정기적인 사이트 점검 및 업데이트 계획
    - 로그인 및 회원 가입 기능
        - 사용자는 로그인 및 회원 가입을 할 수 있으며 이를 통해 웹 상으로 TIL 관리가 가능
- 사용자 피드백 수집 및 개선 작업