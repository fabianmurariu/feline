# feline
clojure functional helpers starting with basic functor able to map over nested structures

This library emerged from a simple need to map over nested collections like: increment the value
of a counter in a list of maps

## fmap

``` clojure
feline.core> (map #(into {} (map (fn [[k v]] [k (inc v)]) %)) [{:a 1} {:b 2}])
({:a 2} {:b 3})
```
That keeps the maps as they are (so far I'm fine with that, maybe a lazy version would be justified)

But that is ugly, instead

``` clojure
feline.core> (fmap inc [{:a 1 :b 2} {:c 3}])
({:a 2, :b 3} {:c 4})
```
fmap applies the function over the value of the map not the key

What about nested lists? No problem

``` clojure
feline.core> (fmap inc [[[[1]]] [[2]] [3] 4])
((((2))) ((3)) (4) 5)
```

## nmap

You may not want to map all the way into your structure, nmap takes an int that limits how deep you go and apply your function

``` clojure
feline.core> (nmap count [[[1 2 3]] [[2 3]] [[1] []]] 2)
((3) (2) (1 0))
```

Or use the shortcut

``` clojure
feline.core> (nmap2 count [[[1 2 3]] [[2 3]] [[1] []]])
((3) (2) (1 0))
```

As usual nested maps in lists are the real heroes

``` clojure
feline.core> (nmap2
    (fn [k] (if (map? k) (into k {:type "person"} ) k) )
    [{:info {:name "Jane"} :address 26} {:info {:name "John"} :age 28}])
({:info {:name "Jane", :type "person"}, :address 26}
{:info {:name "John", :type "person"}, :age 28})
```
