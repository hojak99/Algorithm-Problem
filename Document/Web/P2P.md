# P2P (Peer to Peer)
이번에 `P2P` 에 대한 문서를 작성하는 이유는 좀 더 자세하고 확실하게 알기 위해서이다. 딱히 `P2P` 에 관심을 가지고 개발할 일이 없다보니 단순히 `파X노리 같은 P2P 방식의 파일 다운로드 서비스` 라고 생각하고 있었다. 이제 한 번 `P2P` 에 대해서 알아보자~


## Introduction
> `P2P` 란 기존의 서버와 클라이언트 개념이나 공급자와 소비자 개념에서 벗어나 개인 컴퓨터끼리 직접 연결하고 검색함으로써 모든 참여자가 공급자인 동시에 수요자가 되는 형태

참고로 `P2P` 방식은 크게 2가지 방식이 있다. 
- 서버의 도움없이 컴퓨터끼리 직접 통신하는 것
- 서버의 도움을 받아 컴퓨터끼리 통신하는 것
---

`P2P` 의 정의는 위와 같다고 한다. 서버의 도움을 받는 예를 들어보겠다.

> `A` 란 사람은 `파x노리` 란 사이트에서 `a.txt` 란 파일을 등록하였다. `B` 라는 사람이 `A` 가 등록한 `a.txt` 란 파일을 다운로드 받았다.

위와 같은 상황일 때 `B` 라는 사람은 서버에서 `a.txt` 를 다운받는 것이 아닌, 서버에서 `a.txt` 를 올린 사용자를 찾아(`A`란 사용자) 해당 사용자의 IP 주소와 `B` 라는 사람의 IP 주소를 이용해 파일을 다운로드 할 수 있도록 한다는 뜻이다.


두 번째 예이다. 이 예도 서버의 도움을 받는 예시이다. 

> `BitTorrent` 라는 프로토콜은 `피어(Peer)` 들의 정보를 저장, 관리하는 `트래커(Tracker)` 라는 서버 기반으로 동작한다. `BitTorrent` 프로토콜의 동작을 보면, 파일을 다운로드 받고자 하는 `BitTorrent 클라이언트` 는 `트래커`에게 해당 파일을 공유하고 있는 `피어 리스트`를 요청하고, 요청을 받은 `트래커`는 해당 파일을 공유하고 있는 `피어`들 중에 무작위로 50개의 `피어`를 선정하여 `피어 리스트`를 작성하고 이를 클라이언트에게 보내서 `피어 리스트`를 수신한 클라이언트는 이를 기반으로 `피어`들과 통신을 통해 파일을 다운로드 받는다.

해당 프로토콜의 무작위 피어 선정 방식을 문제점으로 지적하고 있다. 왜냐하면 `피어 선정 시 피어들이 어떤 네트워크에 속해 있는지 고려하지 않기 때문` 이다. 

그렇기 때문에 `P4P`가 등장했다고 한다.

이 문서는 `P2P` 만을 다루기 때문에 `P4P` 에 대해서는 간단한 정의만 알아보도록 하겠다.

> `P4P` 는 `ISP(Internet Service Provider)` 의 네트워크 정보를 관리하는 서버를 `ISP` 내에 구축하고 해당 서버와 트래커 간의 통신을 한다. 그래서 트래커가 피어 리스트를 작성할 때 클라이언트와 동일한 `ISP` 에 존재하는 피어들을 선정하게 된다.





BitTorrent 관련 내용 출처 : https://www.netmanias.com