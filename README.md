**Roman Numbers Equation Solver**

  From time to time its a good idea to work on a project implementing every functionality from scratch. For example, 4 years ago I did it in PHP, I called it FFS - which stands for Framework from scrach.
You may find it [here](https://github.com/virgiliolino/ffs).

  In this repo instead, you'll something like this in Java, a project I worked in 2015. The use case is a Roman Numbers First Degree Equation Solver, where you can code the value of symbols and solve equations.
I also wanted to test some technics of batch processing to validate some personal convinctions. The software will process a set of definitions and equations and provide a result for the equations.

  To notice that I splitted it somehow into commands and queries. Definitions are commands that don't return anything, queries, are equations that return a value.

A sample of a file that you may pass from the command line is:

```SampleSymbol is I
SampleSymbol is V
Ten is X
Fifty is L
SampleSymbol SampleSymbol Silver is 34 Credits
SampleSymbol SampleSymbol kiwi is 57800 Credits
ThirdSymbol ThirdSymbol Chair is 3910 Credits
how much is Ten Fifty SampleSymbol SampleSymbol ?
how many Credits is SampleSymbol AnotherSymbol Silver ?
how many Credits is SampleSymbol AnotherSymbol Kiwi ?
how many Credits is SampleSymbol AnotherSymbol Chair ?```

Enjoy,
Virgilio



