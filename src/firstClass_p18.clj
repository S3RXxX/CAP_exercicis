(ns firstClass_p18)

;1
(defn fn21 [lst]
    ((comp (partial apply +) (partial filter odd?) (partial map count)) lst)
  )

(println "fn1 4:" (fn21 '("a" "bb" "ccc")))

(println)

;2
(defn fn22 [lst]
  ((comp (partial map #(* % %)) (partial take 5) (partial filter #(not= 0 (mod % 3)))) lst)
)

(println "fn22: (1 4 16 25 25):" (fn22 '(1 2 3 4 0 5 6 5 5 5 5 5 5 5)))
