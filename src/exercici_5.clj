(ns exercici_5)

;; apartat 1
(def abs-value #((if (< % 0) - +) %))
(println "1: -666" (abs-value -666))
(println "1: 5" (abs-value 5))

;; apartat 2