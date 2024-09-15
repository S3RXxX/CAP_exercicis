(ns exercici_1_5)

;; exercici 1
(def x 2)
(println "exercici 1:" (+ 1 (/ (+ 1 (* 2 x)) 3) (* x x)))

;;exercici 2
(def n 25)
(println "exercici 2:" (or (and (<= 10 n) (>= 20 n)) (and (<= 30 n) (>= 40))))

;; exercici 3
(defn parell? [k] (= 0 (mod k 2)))
(println "exercici 3: 2" (parell? 2))
(println "exercici 3: 243" (parell? 243))

;; exercici 4
;; assumeixo que pot ser negativa :3
(defn notes [n]
    (cond 
        (< n 5) "suspès"
        (< n 7) "aprovat"
        (< n 9) "notable"
        (> n 10) "més de 10 0_0"
        :else "excelent"
    ))
(println "exercici 4:")
(println "3.3:" (notes 3.3))
(println "5:" (notes 5))
(println "5.1:" (notes 5.1))
(println "6.9:" (notes 6.9))
(println "7.8:" (notes 7.8))
(println "9.3:" (notes 9.3))
(println "10:" (notes 10))
(println "10.2:" (notes 10.2))
