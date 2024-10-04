(ns llistes_comprensio)

;1
(defn my-map [f col]
  (for [x col] (f x))
  )

(println "my-map (2 4 6 8): " (my-map #(* % 2) (range 1 5)))

(println)

;2
(defn my-filter [f col]
  (for [x col] :when (f x))
  )

(println "my-filter (1 3): " (my-filter odd? (range 1 5)))

(println)
