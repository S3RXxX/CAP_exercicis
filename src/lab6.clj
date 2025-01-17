(ns lab6)

;; Exercici 1
(defn memoize2 [f]
  (let [mem (atom {})]
    (fn [& args]
      ;(println "before" args)
      (if-let [e (find @mem args)]
        (val e)
        (let [ret (apply f args)]
          ;(println "after" args)
          (swap! mem assoc args ret)
          ret)))))

(defn fib [n]
  ;(println n)
  (if (<= n 1)
    n
    (+ (fib (dec n)) (fib (- n 2)))))


(println "Exercici 1:")

(println (time (fib 35)))

(def fib (memoize fib))

(println (time (fib 35)))
(println)

;; memoize funciona com un decorador que afegeix un
;; atom d'un diccionari per no recalcular valors ja calculats
;; --> haver de fer menys càlculs --> menys temps de execució


;; Exercici 2 (passar a do when)

(defn punt []
  (let [coords (atom [0 0])]
  (fn
    ([kw]
     (let [[x y] (deref coords)]
     (cond (= kw :crt) (deref coords)
           (= kw :plr) (list (Math/sqrt (+ (* x x) (* y y))) (/ (* (Math/atan2 y x) 180) Math/PI))
           (= kw :rst) (reset! coords [0 0])
           (= kw :inx) (swap! coords (fn [v] (let [[x y] v] [(inc x) y])))
           (= kw :iny) (swap! coords (fn [v] (let [[x y] v] [x (inc y)])))

         )))
    ([kw p]
      (let [[x y] (deref coords)]
        (cond (= kw :dst)
              ((comp Math/sqrt (partial reduce +) (partial map #(* % %)) (partial map -)) [x y] (p :crt)))))) 
    
    )
    )
  

(println "Exercici 2")
(def p (punt))
(println (p :inx))
(def q (punt))
(println (q :iny))
(println (p :dst q))

(println (q :crt))
(println (q :plr))

