(ns exercicisLlistat)

;1
(fn? range)  ;; és la funció range
(fn? (first '(range 10)))  ;; és el símbol range (com una string), pq has posat ' range és la string range

;2 concat-elements
(defn concat-elements [ss]
  (reduce concat '() ss)
  )

(println "concat-elements")
(println (concat-elements []))                ; ()
(println (concat-elements [[:a :b]]))       ; (:a :b)
(println (concat-elements [[10 20] [30 40]])) ; (10 20 30 40)
(println)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;3 str-cat a-seq
(defn str-cat [ss]
  (reduce #(concat %1 " " %2) "" ss)
  )

(println "str-cat")
(println (str-cat ["Sóc" "del" "Barça"]))   ; "Sóc del Barça"
(println (str-cat ["Ya" "si" "eso"]))       ; "Ya si eso"
(println (str-cat ["quants" " " "espais"])) ; "quants   espais"
(println (str-cat []))                      ; ""
(println)


;4
(defn interposar [x s]
  1
  )


(interposar 0 [1 2 3])           ; (1 0 2 0 3)
(interposar "," ["Yo" " mi " " me "]) ; (" Yo " "," " mi " "," " me ")
(interposar :a [1])              ; (1)
(interposar :a [])               ; ()
