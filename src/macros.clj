(ns macros)

;; Lab macros

;; unless
(defmacro unless [test & body] 
  (list 'if `(not ~test) (cons 'do body))
  )

(println "unless")
(println (unless false (println "Aquest missatge es mostra perquè la condició és falsa.")))
(println (unless true (println "Aquest missatge no es mostra perquè la condició és certa.")))
(println)

;; bulce foreach
(defmacro foreach [[simbol iterable :as params] & cos]
  (cond (not (vector? params))
        (throw (Error. "El 1r argument ha de ser un vector amb el format adequat"))
        (not= 2 (count params))
        (throw (Error. "Calen exactament 4 elements per definir el for"))

        :else
            `(loop [~simbol (first ~iterable) restant# (seq ~iterable) valor# nil]
               (if (not (empty? restant#))
                 (let [nou-valor# (do ~@cos)
                       nou-restant# (rest restant#)
                       nou-simbol# (first nou-restant#)]
                   (recur nou-simbol# nou-restant# nou-valor#))
                 valor#))))

(println "foreach")
(println (foreach [x [1 2 3]] (println (inc x))))
(println)

;; Composició de funcions
(defmacro cf [& lf]
     (let [lpf# (map #(if (seq? %) `(partial ~@%) %) lf)] 
           `(comp ~@lpf#)
           )
       
  )

(println "Composició de funcions")

(def expressio (cf inc +))
(println (expressio 2 4))  ;; 7 

(def producte-escalar (cf (apply +) (map *)))
(println (producte-escalar [1 2 3] [2 2 2]))  ;; 12

(def numParells (cf count (filter even?)))
(println (numParells [2 3 4]))  ;; 2


(defn consumeix [f x y]
  (list (partial f x) y)
  )

(println "Consumeix")
(def numVegades (cf count (apply filter) (consumeix =)))
(println (numVegades 3 [3 2 3]))
(println)
