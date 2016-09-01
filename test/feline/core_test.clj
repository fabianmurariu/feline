(ns feline.core-test
  (:require [clojure.test :refer :all]
            [feline.core :refer :all]))

(deftest nested-fmap-test
  (testing "map over nil"
    (is (= true (fmap nil? nil))))
  (testing "map over any value"
    (is (= "BIGGER" (fmap clojure.string/upper-case "bigger")))
    (is (= 5 (fmap inc 4))))
  (testing "map over a vector"
    (is (= [2 3 4 5] (fmap inc [1 2 3 4]))))
  (testing "map over a map"
    (is (= {:A 1 :B 2 :C 3} (fmap inc {:A 0 :B 1 :C 2}))))
  (testing "map over a sequence"
    (is (= [3 4 5 6] (fmap inc (map inc [1 2 3 4])))))
  (testing "map over a map of arrays"
    (is (= [[2 3] [4 5]] (fmap inc [[1 2] [3 4]]))))
  (testing "map over an array of maps"
    (is (= [{:a 2} {:b 3 :c 4}] (fmap inc [{:a 1} {:b 2 :c 3}]))))
  (testing "map over a map of maps"
    (is (= {:a {:b 2} :b {:c 3}} (fmap inc {:a {:b 1} :b {:c 2}}))))
  (testing "inc all over no matter how deep"
    (is (= [1 [[[2]]] [3]] (fmap inc [0 [[[1]]] [2]])))))

(deftest nested-limited-fmap
  (testing "map once over nested array"
    (is (= [1 2 3] (nmap1 count [[1] [2 3] [4 5 6]]))))
  (testing "map inside an array inside a nested map"
    (is (= [{:a {:b 1 :c 2}} {:x {:b 2 :c 1} :y {}}]
           (nmap3 count [{:a {:b [1] :c [1 2]}} {:x {:b [3 4] :c [5]} :y {}}])))))
