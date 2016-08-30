(ns feline.core)
;; This is a nested map function that should go into every complex
;; structure and map over it's components until the value is no
;; longer a seq, on maps the function is applied right handed aka on the value and it's eager

(defmulti fmap
  "Applies function f to each item in the data structure s and returns
   a structure of the same kind."
  {:arglists '([f s])}
  (fn [f s] (type s)))

(defmethod fmap clojure.lang.IPersistentVector
  [f v]
  (map (partial fmap f) v))

(defmethod fmap clojure.lang.IPersistentMap
  [f m]
  (into (empty m) (map (fn [[k v]] [k (fmap f v)]) m)))

(defmethod fmap clojure.lang.LazySeq
  [f v]
  (map (partial fmap f) v))

(defmethod fmap clojure.lang.IPersistentList
  [f v]
  (map (partial fmap f) v))

(defmethod fmap clojure.lang.IPersistentSet
  [f s]
  (map (partial fmap f) s))

(defmethod fmap java.lang.Object
  [f v]
  (f v))

(defmethod fmap nil
  [_ _]
  nil)
