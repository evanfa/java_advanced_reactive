## RxJava - Reactive Programming
# Reactive Manifesto
Required to follow the principles of the [Reactive Manifesto](https://duckduckgo.com "The best search engine for privacy").
<ul>
<li>Responsive – systems should respond in a timely manner</li>
<li>Message Driven – systems should use async message-passing between components to ensure loose coupling</li>
<li>Elastic – systems should stay responsive under high load</li>
<li>Resilient – systems should stay responsive when some components fail</li>
</ul>

## Basics
<ul>
<li>Observable represents any object that can get data from a data source and whose state may be of interest in a way that other objects may register an interest</li>
<li>An observer is any object that wishes to be notified when the state of another object changes</li>
</ul>

# Base Clases
<ul>
<li><b>io.reactivex.rxjava3.core.Flowable:</b> 0..N flows, supporting Reactive-Streams and backpressure.</li>
<li><b>io.reactivex.rxjava3.core.Observable:</b> 0..N flows, no backpressure.</li>
<li><b>io.reactivex.rxjava3.core.Single:</b> a flow of exactly 1 item or an error.</li>
<li><b>io.reactivex.rxjava3.core.Completable:</b> a flow without items but only a completion or error signal.</li>
<li><b>io.reactivex.rxjava3.core.Maybe:</b> a flow with no items, exactly one item or an error.</li>
</ul>

# Flowable
Flowable
implements the Reactive-Streams Pattern, which is a standard for asynchronous stream processing with non-blocking backpressure.
<ul>
<li>Support Observable emitting more rapidly than observer consume</li>
<li>Operators: <b>map</b>,<b>filter</b>,<b>reduce</b></li>
<li>Schedulers</li>
<li>Hot & Cold Flowables: lowable
can be either "hot" (it can start emitting items as soon as it's created) or "cold" (it doesn't start emitting items until a subscriber starts to observe).</li>
</ul>

### Difference between map and reduce
#### map()
The map operator is used to transform the items emitted by an Observable by applying a function to <u>each item</u>. The function transforms the item, and the transformed item is then emitted by the Observable.
```java
Flowable.range(1, 5)
    .map(v -> v * 2)
    .subscribe(System.out::println);
```
#### reduce()
The reduce operator is used to <u>combine all items</u> emitted by an Observable into a single item by applying a function that takes two parameters: the accumulated value and the current item. The function returns a new accumulated value, which is then used in the next call to the function.
```java
Flowable.range(1, 5)
    .reduce(0, (total, next) -> total + next)
    .subscribe(System.out::println);
```

# Schedulers
Schedulers in RxJava are used to specify where and when tasks are executed, whether it be on a new thread, a pooled thread, or the current thread.
## Schedulers.newThread()
This creates a new thread for each unit of work scheduled.
```java
Flowable.range(1, 5)
.subscribeOn(Schedulers.newThread())
.map(v -> v * v)
.blockingSubscribe(System.out::println);
```

## Schedulers.io()
This maintains a pool of threads for IO-bound work.
In this example, the work is done on one of the pooled threads. This is useful for IO-bound work like reading and writing to disk, network calls, database queries, etc.
```java
Flowable.range(1, 5)
    .subscribeOn(Schedulers.io())
    .map(v -> v * v)
    .blockingSubscribe(System.out::println);
```
## Schedulers.computation()
This maintains a fixed number of threads based on the number of available processors. It's intended for computational work.
In this example, the work is done on one of the computation threads. This is useful for CPU-intensive work like calculations and transformations.
```java
Flowable.range(1, 5)
.subscribeOn(Schedulers.computation())
.map(v -> v * v)
.blockingSubscribe(System.out::println);
```
## Schedulers.single()
This executes all tasks on a single thread.
```java
Flowable.range(1, 5)
.subscribeOn(Schedulers.single())
.map(v -> v * v)
.blockingSubscribe(System.out::println);
```
## Schedulers.trampoline()
This executes tasks in a FIFO manner on the immediate scheduler.
```java
Flowable.range(1, 5)
.subscribeOn(Schedulers.trampoline())
.map(v -> v * v)
.blockingSubscribe(System.out::println);
```