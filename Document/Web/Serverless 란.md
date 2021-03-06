# Serverless 란?
> Serverless 란 서버가 없다는 의미가 아닌 BaaS(Backend as a Service) 또는 FaaS(Function as a Service) 에 의존해 작업을 처리한다는 의미이다.

정의는 위와 같은데 쉽게 생각하면 `전산실이 없다` 라고 생각하면 될 것 같다.   
보통 회사마다 하드웨어, 네트워크, 운영체제 등 인프라를 관리하는 전산실이 있을 것이다. 그 대신 AWS lambda라던가 FireBase 같은 것을 사용한단 뜻이다.

## BaaS (Backend as a Service)
Firebase 같은 것을 생각하면 된다. 개발에 있어서 필요한 다양한 기능들(DB, SNS 연동 등)을 API 로 제공해 줌으로써, 쉽고 빠르게 구현할 수 있게 해주고 비용은 사용한 만큼 나가게 된다. 서버의 이용자가 순식간에 늘어나게 돼도, 알아서 확장이 된다.

## FaaS (Function as a Service)
Aws Lambda 같은 것을 생각하면 된다. 프로젝트를 여러 개의 함수로 쪼개서, 함수를 등록 후 이 함수들이 실행되는 횟수(그리고 실행된 시간)만큼 비용을 내는 방식이다. 

여기서 PaaS 와 서버 시스템에 대해서 신경쓰지 않아도 되는 점이 PaaS 와 유사하다고 하는데, PaaS 의 경우 애플리케이션 배포 후 애플리케이션이 서버에서 24시간동안 계속 돌아가고 있다는 것이고, FaaS 는 애플리케이션이 아닌 함수를 배포해 특정 이벤트가 발생 했을 때 실행되며, 실행된 후 작업이 끝나면 종료한다고 한다.


## 헷갈리는 점
그렇다면 AWS, Azure 같은 서비스는 Serverless 가 아니냐는 것이다. 

AWS, Azure 같은 서비스가 IaaS 라고 불리는데 처음에 나는 AWS 같은 경우도 사용자가 직접 하드웨어를 관리하지 않으니까(전산실같이) 서버리스가 아닐까? 라고 생각했다. 

하지만 그래서 더 많이 헷갈렸으나 AWS 는 그냥 가상컴퓨팅환경을 제공해주는 것이고, AWS Lambda 는 서버 애플리케이션 환경 자체를 구축해주기 때문에 다른 개념이라고 하는 말씀하시는 분이 계셔서 이해가 갔다,,

즉, Serverless 는 서버 애플리케이션 환경 자체를 구축해준다고 생각하면 될 것 같다. 

`AWS, Azure 은 Serverless 는 아니다.`