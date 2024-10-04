(ns first_class)
;; exercicis Jutge Ús d'ordre superior
;1

(defn eql [lst1 lst2]
  (and (= (count lst1) (count lst2))
       ((comp (partial every? true?) (partial map =)) lst1 lst2))
  )


(println "eql true: " (eql '(1 2 3) '(1 2 3)))
(println "eql false: " (eql '(1 2 3) '(3 2 1)))
(println "eql false: " (eql '(1 2 3) '(1 2 3 4)))

(println)

;2
(defn prod-of-evens [lst]
  ((comp (partial apply *) (partial filter even?)) lst)
  )

(println "prod-of-evens 20:" (prod-of-evens '(2 10 5)))
(println "prod-of-evens :" (prod-of-evens '(3 1 5)))

(println)

;3
;(defn my-reverse [lst])
;reduce
;(println "my-reverse (3 4 7 8 7): " (my-reverse '(7 8 7 4 3)))

(println)

;4
(defn scalar-product [lst1 lst2]
  ((comp (partial apply +) (partial map *)) lst1 lst2)
  )

(println "scalar-product 8.0: " (scalar-product '(2.0 1.0) '(3.0 2.0)))

(println)

;5
(defn count-in [l x]
  ((comp (partial map count) (partial map (partial filter #(= x %)))) l)
  )

(println "count-in (2 1 0 0): " (count-in '((3 2 3) (3) () (2 2)) 3))

(println)

;6
(defn first-word [s]
  ((comp (partial apply str) (partial take-while #(not= \space %)) (partial drop-while #(= \space %))) s)
  )

(println "first-word Volem: " (first-word "  Volem pa amb oli"))

(println)
