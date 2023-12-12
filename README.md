## RxJava - Reactive Programming

## Base Classes
<ul>
<li><em>io.reactivex.rxjava3.core.Flowable:</em> 0..N flows, supporting Reactive-Streams and backpressure</li>
<li><em>io.reactivex.rxjava3.core.Observable:</em> 0..N flows, no backpressure,</li>
<li><em>io.reactivex.rxjava3.core.Single:</em> a flow of exactly 1 item or an error,</li>
<li><em>io.reactivex.rxjava3.core.Completable:</em> a flow without items but only a completion or error signal,</li>
<li><em>io.reactivex.rxjava3.core.Maybe:</em> a flow with no items, exactly one item or an error.</li>
</ul>

## Reactive Manifesto
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

## Base Clases
<ul>
<li><b>io.reactivex.rxjava3.core.Flowable:</b> 0..N flows, supporting Reactive-Streams and backpressure.</li>
<li><b>io.reactivex.rxjava3.core.Observable:</b> 0..N flows, no backpressure.</li>
<li><b>io.reactivex.rxjava3.core.Single:</b> a flow of exactly 1 item or an error.</li>
<li><b>io.reactivex.rxjava3.core.Completable:</b> a flow without items but only a completion or error signal.</li>
<li><b>io.reactivex.rxjava3.core.Maybe:</b> a flow with no items, exactly one item or an error.</li>
</ul>