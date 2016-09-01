(ns feline.core)
;; This is a nested map function that should go into every complex
;; structure and map over it's components until the value is no
;; longer a seq, on maps the function is applied right handed aka on the value and it's eager

(defmulti fmap
  "Applies function f to each item in the data structure s"
  {:arglists '([f s])}
  (fn [f s & n] (if (map? s) :map
                    (if (coll? s) :coll))))

(defmethod fmap :coll
  ([f v]
   (map (partial fmap f) v)))

(defmethod fmap :map
  ([f m]
   (into (empty m) (map (fn [[k v]] [k (fmap f v)]) m))))

(defmethod fmap :default
  ([f v]
   (f v)))

;; Nested map with count

(defmulti nmap
  "Applies function f to each item in the data structure s"
  {:arglists '([f s n])}
  (fn [f s ^java.lang.Long n]
    (if (map? s)
      [:map (= n 0)]
      (if (coll? s)
        [:coll (= n 0)]))))

(defmethod nmap [:map false]
  ([f v n]
   (into (empty v) (map (fn [[k v1]] [k (nmap f v1 (- n 1))]) v))))

(defmethod nmap [:coll false]
  ([f c n]
   (map #(nmap f % (- n 1)) c)))

(defmethod nmap :default
  [f v & n]
  (f v))


(defn nmap1
  "this applies f to the first level, it behaves similar to map (but it is eager on maps), it also applies to v on maps"
  [f v] (nmap f v 1))

(defn nmap2 [f v] (nmap f v 2))
(defn nmap3 [f v] (nmap f v 3))
(defn nmap4 [f v] (nmap f v 4))
(defn nmap5 [f v] (nmap f v 5))
(defn nmap6 [f v] (nmap f v 6))

;; want more make your own
