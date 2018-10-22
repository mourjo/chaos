# My experiments with concurrency through `chaos`

As the heading says, this project is mainly for my own understanding of concurrency principles from multiple sources, using Java. The goals I am trying to achieve:

- Understand how complicated concurrency is in practice (I'm also reading `Java Concurrency in Practice` for this purpose)
- Understand how Java proposes to solve the concurrency problems by trying to solve problems myself
- Document it through source so that later I can read my code and recall what I studied
- Help anyone who might start out as a novice like me with these examples

Pull requests are welcome, suggestions or mistakes in my code will be much appreciated!


## Why call it "chaos"?
The whole idea of this project came to me when someone asked me, given that I code in `Clojure` for a living, "Do you know understand future threads are managed in Clojure?", and my lack of understanding came in the way of writing better code.

So, as much as I love the brilliant high level constructs like `core.async` of Clojure, in this project I try to understand how the bare metal in Java works, in a quest to ultimately understand `Clojure` better and in doing so, understand the chaos that concurrency can pose to be.


# License
Copyright © 2018 Mourjo
Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
