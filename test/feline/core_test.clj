(ns feline.core-test
  (:require [clojure.test :refer :all]
            [feline.core :refer :all]))

(deftest nested-fmap-test
  (testing "map over nil"
    (is (= nil (fmap inc nil))))
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
    (is (= {:a {:b 2} :b {:c 3}} (fmap inc {:a {:b 1} :b {:c 2}})))))
