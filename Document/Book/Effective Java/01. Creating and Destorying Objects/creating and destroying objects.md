# Creating and Destroying Objects
객체의 생성과 삭제에 대해 살펴본다.


## 생성자 대신 정적 팩토리 메소드를 사용할 수 없는지 생각해보자

> 정적 팩토리 메소드 : 객체 생성을 캡슐화하는 기법. 객체를 생성하는 메소드를 만들고, static 으로 선언하는 기법이다. 그 예로 `valueOf` 메소드를 들 수 있다.

```
public static Boolean valueOf(boolean b) {
    // 삼항연산자
    return b ? Boolean.TRUE : Boolean.FALSE;
}

// 이렇게 반한된 Boolean 객체는 == 로 비교할 수 있다.
```

#### 장점
- 생성자와 달리 정적 팩토리 메소드에는 이름이 있음 (가독성 높아짐)
- 생성자와 달리 호출할 때마다 새로운 객체를 생성할 필요 없음
- 생성자와 달리 하위 자료형 객체를 반환할 수 있음
- 형인자 자료형 객체를 만들 때 편하다

#### 단점
- public, protected 로 선언된 생성자 없어서 하위 클래스를 못 만듦
- 다른 정적 메소드와 확연히 구분되지 않음

> 형인자 자료형 객체는 다음을 말한다. `Map<String, List<String>> m = new HashMap<String, List<String>();` 여기서 형인자를 연달아 두 번 사용하는데 Jdk 1.7 부터는 생성자를 호출할 때도 자료형 유추가 가능해서 정적 팩토리 메소드를 추가할 필요가 없어졌다.


- `valueOf` : 인자로 주어진 값과 같은 값을 갖는 객체를 반환. 형변환 메소드라고 한다
- `of` : `valueOf` 를 더 간단하게 쓴 것. EnumSet 덕분에 인기를 모음 이름
- `getInstance` : 인자에 기술된 객체를 반환하지만 같은 값을 갖지 않을 수도 있음. 싱글톤일 경우 인자없이 항상 같은 객체 반환
- `newInstance` : getInstance 와 같지만 호출할 때마다 다른 객체 반환
- `getType` : getInstance 와 같지만 반환될 객체의 클래스와 다른 클래스에 팩토리 메소드가 있을 때 사용. `Type` 은 팩토리 메소드가 반환할 객체의 자료형
- `newType` : `newInstance` 와 같지만, 반환될 객체의 클래스와 다른 클래스에 팩토리 메소드가 있을 때 사용. `Type` 은 팩토리 메소드가 반환할 객체의 자료형


> 검색해보니 정적 팩토리 메소드에 대해 비판적인 의견도 있다.  
> https://dzone.com/articles/constructors-or-static-factory-methods


---

## 생성자 인자가 많을 때는 Builder 패턴 적용을 고려하라
선택적 인자가 많은 상황에서는 생성자를 엄청나게 많이 정의해놓아야 하는 경우가 있다. effective java 에서는 그 예로 성분표에 대한 예를 들었는데, 한 번 성분표에 대한 예를 보겠다.

```
public class NutritionFacts {
    private final int servingSize;      // 필수
    private final int servings;         // 필수
    private final int calories;         // 선택
    private final int fat;              // 선택
    private final int sodium;           // 선택
    private final int carbohydrate;     // 선택

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }   

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
```

위의 클래스로 객체를 생성할 때는 해당 인자 개수에 맞는 생성자를 골라 호출하면 된다.

```
NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 3, 35, 27);
```

이렇게 하다보면, 설정할 필요가 없는 필드에도 인자를 전달해야 하는 경우가 생긴다. 인자 수가 늘어나면 정말 곤란해진다.

> 점층적 생성자 패턴은 잘 동작하지만 인자 수가 늘어나면 클라이언트 코드를 작성하기 어려워지고, 무엇보다 읽기 어려운 코드가 된다.

그냥 처음에 인자 없는 생성자를 호출해 객체를 먼저 만들고, setter 를 호출해 값을 넣어줄 수 있으나 이렇게 된다면 immutable 클래스를 만들 수 없다는 것이다. 

이제 빌더 패턴을 알아보자. 필요한 객체를 직접 생성하는 대신, 클라이언트는 먼저 필수 인자들을 생성자에 전부 전달해 빌더 객체를 만들고 빌더 객체에 정의된 설정메소드들을 호출해 선택전 인자들을 추가해 나가서 마지막에 아무런 인자 없이 build 메소드를 호출해 변경 불가능한 객체를 만드는 것이다. 여기서 빌더 클래스는 빌더가 만드는 객체 클래스의 정적 멤버 클래스로 정의한다.

아래의 예를 보쟈

```
// 빌더 패턴
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 인자
        private final int servingSize;
        private final int servings;
        
        // 선택 인자
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
```

위의 코드를 보면 NutritionFacts 객체가 변경 불가능하다는 사실과 모든 인자의 기본값이 한곳에 모여 있다는 것을 알 수 있다.

```
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
    .calories(100).sodium(35).carbohydrate(27).build();
```

이렇게 유연하게 코드를 작성할 수 있다. 하지만 단점도 존재한다. 객체를 생성하려면 우선 빌더 객체를 생성해야 하는데 성능이 중요한 곳에서는 오버헤드 때문에 문제가 발생할 가능성도 있다고 한다. 또한, 점층적 생성자 패턴보다 많은 코드를 요구하기에 인자가 많은 상황에서 사용해야 한다.

> 빌더 패턴은 인자가 많은 생성자나 정적 팩토리가 필요한 클래스를 설계할 때, 특히 대부분의 인자가 선택적 인자인 상황에 유용하다.

---

## private 생성자나 enum 자료형은 싱글턴 패턴을 따르도록 설계하라

> 싱글턴은 객체를 하나만 만들 수 있는 클래스다.

그래서 클래스를 싱클턴으로 만들면 클라이언트를 테스트하기가 어려워질 수가 있다. 싱글턴이 어떤 인터페이스를 구현하는 것이 아니면 가짜 구현으로 대체할 수 없기 때문이다. 

이제 싱글톤을 구현하는 방법에 대해 알아보자

#### 생성자는 private 로 선언, 정적 멤버는 public 을 사용해 final 로 선언
```
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() { .. }

    public void leaveTheBuilding() { .. }
}
```

클라이언트가 해당 객체를 변경할 방법은 없지만 리플렉션을 이용해 권한을 획득해 private 생성자를 호출할 수 있다는 것이다. (AccessibleObject, setAccessible)

그래서 위의 접근을 막기 위해 두 번째 객체를 생성하라는 요청을 받으면 예외를 던지도록 생성자를 고쳐야 한다.

#### public 으로 선언된 정적 팩토리 메소드를 이용한다
```
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }

    public static Elvis getInstance() {
        return INSTANCE;
    }
}
```

`Elvis.getInstance()` 는 항상 같은 객체에 대한 참조를 반환한다. public 필드를 사용하면 클래스가 싱글턴인지는 선언만 보면 금방 알 수 있어서 좋다. 해당 방법의 장점으로는, API를 변경하지 않고도 싱글턴 패턴을 포기할 수 있다는 것이다. 또한, 스레드마다 별도의 객체를 반환하도록 팩토리 메소드를 수정하는 것도 간단하다.

해당 방법 외에는 Elvis 객체를 만들 수 없지만 위에서 말했던 리플렉션을 이용해 객체를 생성하는 것을 막을 순 없다.

위에서 말한 방법들로 구현한 싱글턴 클래스를 직렬화 클래스로 만들려면 클래스 선언 부분에 `implements Serializable` 을 추가하는 것으로는 부족하고, 싱글턴 특성을 유지하려면 모든 필드를 `transient`로 선언하고 `readResolve` 메소드를 추가해야 한다. 그렇지 않으면 역직렬화될 때마다 새로운 객체가 생기게 된다.

```
private Object readResolve() {
    // 동일한 Elvis 객체가 반환되도록 하는 동시에, 가짜 Elvis 객체는 
    // 가비지컬렉터가 처리하도록 만든다
    return INSTANCE;
}
```

#### enum 자료형으로 싱글턴 구현
```
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() { .. }
}
```

이 방법은 public 필드를 사용하는 방법과 동일하다. 좀 더 간결하고, 직렬화가 자동으로 처리된다는 것이다. 

리플렉션을 통한 공격에도 안전하다

싱글톤을 구현하기에 가장 좋은 방법인 것 같다.

---

## 객체 생성을 막을 때는 private 생성자를 사용해라
객체를 만들 수 없도록 할 때 클래스를 `abstract` 로 선언해도 소용없다. 하위 클래스를 정의하면 객체 생성이 가능해지기 때문이다.

```
public class UtilityClass {
    private UtilityClass() {
        throw new AssertionError();
    }

    ...
}
```

다음과 같은 방법을 사용하면 하위 클래스도 만들 수 없다.모든 생성자는 상위 클래스의 생성자를 명시적, 묵시적으로 호출할 수 있어야 하는데 호출 가능한 생성자가 상위 클래스에 없기 때문이다.

---

## 불필요한 객체는 만들지 마라
기능적으로 동일한 객체는 필요할 때마다 만드는 것보다 재사용하는 편이 낫다. 

진짜 피해야 하는 예는 다음과 같다.
```
String s = new String("string");
```

위의 방법보다는 다음과 같은 방법이 바람직하다.

```
String s = "string";
```

처음에 들었던 예에선 new 로 객체를 생성할 때마다 Heap 영역에 생성이 되는데, 반복문에서 위의 코드를 사용하면 매번 heap 에 쌓이게 된다. 하지만 두 번째 예에선 `string constant pool` 에서만 생성이 되고 변경되기 때문에 두 번째 방법이 바람직하다는 것이다.

그리고 아래의 예에서 나오는 메소드를 정의하면 안된다.
```
public class Person {
    private final Date birthDate;

    public boolean isBabyBoomer() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
}
```

위의 예제에서 isBabyBoomer 메소드는 호출될 때마다 Calendar 객체 하나, TimeZone 객체 하나, 그리고 Date 객체 두 개를 쓸데없이 만들어 낸다. 좀 더 효율적인 코드는 정적 초기화 블록을 통해 개선할 수 있다.

```
public class Person {
    private final Date birthDate;

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
```

이렇게 개선된 Person 클래스는 Calendar, TimeZone 그리고 Date 객체를 클래스가 초기화 될 때 한 번만 만든다.

---

## 유효기간이 지난 객체 참조는 폐기하라
Java 는 C, C++ 처럼 손수 메모리 관리를 해주지 않아도 가비지컬렉터가 알아서 메모리 관리를 해준다. 하지만, 메모리 관리를 신경쓰지 않아도 되는 것은 아니다.

아래의 스택 예제코드를 보자.

```
// 메모리 누수가 생기는 코드
public class Stack {
 
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
     
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
     
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
     
    public Object pop() {
        if(size == 0){
            throw new EmptyStackException()
        }
        return elements[--size];
    }
     
    private void ensureCapacity() {
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
```

위의 예제는 메모리 누수가 생기는 코드이다. 가비지 컬렉터가 해야할 일이 많아져서 성능이 저하되거나, 메모리 요구량이 증가할 것이다.

문제가 생기는 메소드는 `pop()` 메소드 부분에서다. 스택에서 pop 할 시에 제거한 객체를 가비지 컬렉터가 처리하지 못해서 생기는 문제다. 즉, 스택이 제거된 객체에 대한 만기 참조(obsolete reference)를 제거하지 않기 때문이다. 여기서 만기 참조란 다시 이용되지 않을 참조를 말한다.

이러한 문제들을 고치기 위해 사용할 일이 없는 객체 참조는 null 로 만드는 것이다. 아래의 코드를 보자

```
public Object pop() {
    if(size == 0) {
        throw new EmptyStackException();
    }

    Object results = elements[--size];
    elements[size] = null;
    return results;
}
```

만기 참조를 null 로 만드는데 나중에 해당 참조 객체를 사용하더라도 `NullPointerException` 이 발생하기 때문에 바로 종료된다는 장점이 있다. 오류는 빨리 알아내는 것이 좋다.

> 객체 참조를 null 처리하는 것은 규범이라기보단 예외적인 조치가 되어야 한다.

만기 참조를 제거하는 가장 좋은 방법은 참조가 보관된 변수가 스코프를 벗어나게 두는 것이다.

---

## 종료자 사용을 피하라
> 종료자(finalizer)는 예측 불가능하며, 대체로 위험하고, 일반적으로 불필요하다.

C++ 과는 다르게 C++의 소멸자와 종료자는 다르다. 자바에서는 더 이상 참조되지 않는 객체에 할당된 공간을 가비지 컬렉터가 알아서 반환하므로 프로그래머가 딱히 할 일이 없다. 소멸자는 메모리 이외의 자원을 반환하는데도 사용되는데, 자바에서 보통 try-finally 블록이 그 용도로 사용된다.

> 종료자는 즉시 실행되리라는 보장이 전혀 없다! 그렇기 때문에 긴급한 작업을 종료자 안에서 처리하면 안된다.

종료자를 사용하더라도, 명시적인 종료 메소드를 정의하고 더 이상 필요하지 않는 객체라면 클라이언트에서 해당 메소드를 호출하도록 해야한다. 물론, 종료 여부는 객체 안에 보관해서 종료자 메소드를 호출한 뒤 그 객체에 대한 메소드를 호출하면 `IllegalStateException`이 던져지도록 해야한다.

예를 들어, OutputStream, InputStream 에 close 메소드가 있다. 

이러한 명시적 종료 메소드는 try-finally 와 함께 쓰인다. 

```
Foo foo = new Foo(..);
try {
    // 작업 수행
    ...
} finally {
    foo.terminate(); // 명시적 종료 메소드 호출
}
```

어쨌든, 자원 반환에 대한 최종적 안전장치를 구현하거나, 그다지 중요하지 않은 네이티브 자원을 종료시키는 것이 아니라면 종료자는 사용하지 말아야 한다.

