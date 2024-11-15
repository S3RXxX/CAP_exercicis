(ns lab8)

;; https://eli.thegreenplace.net/2017/on-recursion-continuations-and-trampolines/

;; Exercicis

;; Exercici 0

(def mes2 (fn [x cont] (cont (+ 2 x))))
(def per3 (fn [x cont] (cont (* 3 x))))
(def mes4 (fn [x cont] (cont (+ 4 x))))
(defn expr_cps [x cont]
  (mes2 x
        (fn [y]
          (per3 y
                (fn [z]
                  (mes4 z
                        (fn [res]
                          (cont res)
                          )
                        )
                  )
                )
          )
        )
  )

(println "Exercici 0: ")
(expr_cps 1 println)
(println)

;; 1 slow_fib
(defn slow_fib [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (slow_fib (dec n)) (slow_fib (dec (dec n)))))
  )

(println "slow_fib")
(println (slow_fib 5))
(println (slow_fib 10))
;;(println (slow_fib 40))
(println)

;; 2 quick_fib
(defn quick_fib [n]
  (letfn [(fib-helper [a b count]
            (if (zero? count)
              a
              (recur b (+ a b) (dec count))))]
    (fib-helper 0 1 n)))

(println "quick_fib")
(println (quick_fib 5))
(println (quick_fib 10))
(println)

;; 3 iter_fib
(defn iter_fib [n]
  (loop [a 0 b 1 count n]
            (if (zero? count)
              a
              (recur b (+ a b) (dec count))
              )
    )
  )

(println "iter_fib")
(println (iter_fib 5))
(println (iter_fib 10))
(println)

;; 4 cps_fib
(defn cps_fib [n cont]
  (if (< n 2) (cont n)
      (recur (dec n)
             (fn [fn1]
               (cps_fib (- n 2)
                              (fn [fn2]
                                (cont (+ fn1 fn2))))
               )
             )
      )
  )

(println "cps_fib")
(println (cps_fib 5 identity))
(println (cps_fib 10 identity))
(println)

;; 5 cps2_fib (trampoline)
(defn cps2_fib [n cont]
  (if (< n 2) #(cont n)
      #(cps2_fib (dec n)
             (fn [fn1]
               (cps2_fib (- n 2)
                        (fn [fn2]
                          (fn [] (cont (+ fn1 fn2)))))))))

(println "cps2_fib")
(println (trampoline (cps2_fib 5 identity)))
(println (trampoline (cps2_fib 10 identity)))
(println)

;; Comparació amb time
(println "Comparació amb time")

(println "slow_fib" (time (slow_fib 15)))

(println "quick_fib" (time (quick_fib 15)))

(println "iter_fib" (time (iter_fib 15)))

(println "cps_fib" (time (cps_fib 15 identity)))

(println "cps2_fib" (time (trampoline (cps2_fib 15 identity))))

(println)
