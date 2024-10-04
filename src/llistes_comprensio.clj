(ns llistes_comprensio)

;1
(defn my-map [f col]
  (for [x col] (f x))
  )

(println "my-map (2 4 6 8): " (my-map #(* % 2) (range 1 5)))

(println)

;2
(defn my-filter [f col]
  (for [x col :when (f x)] x)
  )

(println "my-filter (1 3): " (my-filter odd? (range 1 5)))

(println)

;3
(defn my-zip-with [f lst1 lst2]
  (for [i (range (min (count lst1) (count lst2))) :let [x (nth lst1 i) y (nth lst2 i)]] (f x y))
  )

(println "my-zip-with (1 4 9): " (my-zip-with * (range 1 4) (range 1 4)))

(println)

;4
(defn thingify [lst1 lst2]
  (for [x lst1 y lst2 :when (zero? (mod x y))] [x y])
  )

(println "thingify ([1 1] [2 1] [2 2] [3 1] [4 1] [4 2] [5 1]): " (thingify (range 1 6) (range 1 3)))

(println)

;5
(defn factors [n]
  (for [x (range 1 (inc n)) :when (zero? (mod n x))] x)
  )

(println "factors 24 = (1 2 3 4 6 8 12 24): " (factors 24))
