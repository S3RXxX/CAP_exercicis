(ns firstClass_p18)

;1
(defn fn21 [lst]
    ((comp (partial apply +) (partial filter odd?) (partial map count)) lst)
  )

(println "fn21 4:" (fn21 '("a" "bb" "ccc")))

(println)

;2
(defn fn22 [lst]
  ((comp (partial map #(* % %)) (partial take 5) (partial filter #(not= 0 (mod % 3)))) lst)
)

(println "fn22: (1 4 16 25 25):" (fn22 '(1 2 3 4 0 5 6 5 5 5 5 5 5 5)))
(println)

;5
(defn fn25 [s]
  (let [roman-to-int {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}
        values (map roman-to-int s)]
    (reduce 
     (fn [acc [curr  nextt]]
       (if (and nextt (< curr nextt))
         (- acc curr)
         (+ acc curr)
         )
       )
     0
     (partition 2 1 (concat values [0]))
     ) 
    )
  )

(println "fn 25: 3 -->" (fn25 "III"))
(println "fn 25: 6 -->" (fn25 "VI"))
(println "fn 25: 4 -->" (fn25 "IV"))
(println "fn 25: 68 -->" (fn25 "LXVIII"))
(println "fn 25: 1994 -->" (fn25 "MCMXCIV"))
