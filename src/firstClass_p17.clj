(ns firstClass_p17)

;1
(defn fn1 [lst]
     ((comp (partial reduce +) (partial map (partial * 2)) (partial filter even?)) lst)
)

(println "fn1 4 :" (fn1 '(1 2 3)))
(println "fn1 16 :" (fn1 '(2 2 3 4)))

(println)

;2
(defn fn2 [noms_edats]
    (vec ((comp sort (partial map first) (partial filter (fn [[nom edat]] (> edat 18)))) noms_edats))
)

(println "fn2 [Amadeus Bartholomeus Holmes]:" (fn2 {"Amadeus" 35 "Rodolfus" 18 "Holmes" 21 "Bartholomeus" 19 "Josephine" 15}))

(println)

;3
(defn fn3 [v]
    (vec ((comp (partial map (partial * 10)) (partial take 3) (partial filter pos?)) v))
)

(println "fn3 [10 30] :" (fn3 [1 -2 3]))
(println "fn3 [20 30] :" (fn3 [2 -2 3 -4]))
(println "fn3 [20 30 70] :" (fn3 [2 -2 3 -4 -5 7 -12 78]))

(println)

;4
(defn fn4 [v]
    ((comp (partial reduce +) (partial filter #(<= 100 %)) (partial map #(let [{quantitat :quantitat preu :preu} %](* preu quantitat)))) v)
)

(println "fn4 400 :" (fn4 [{:quantitat 3 :preu 12} {:quantitat 1 :preu 100} {:quantitat 2 :preu 150}]))

(println)

;5
(defn fn5 [lst]
    ((comp reverse sort (partial map (partial * 3)) (partial filter #(>= % 5))) lst)
)

(println "fn5 (21 18 15) :" (fn5 '(1 2 6 1 2 5 7)))
(println "fn5 (27 24 21 18 15) :" (fn5 '(1 3 5 7 9 2 6 4 8)))
