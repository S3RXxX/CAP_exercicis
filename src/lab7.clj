(ns lab7)

;; 1.1 freqs
(defn freqs [lst]
  ;(->> lst (map #(list % 1)) (reduce #() ))
  (reduce #(if (not (%1 %2)) (assoc %1 %2 1) (assoc %1 %2 (inc (%1 %2)))) {} lst)
  )

(println "freqs")
(println "{1 2, 2 2, 3 2}-->: " (freqs '(1 2 3 2 1 3)))

(println)

;; 1.2 paraules-inici-fi
(require '[clojure.string :as str])
(defn paraules-inici-fi [s c1 c2]
  (->> (str/split s #" ") (filter #(= c1 (first %))) (filter #(= c2 (last %))))
  )

(println "paraules-inici-fi")
(println "(gos galetes)-->: " (paraules-inici-fi "el gos menja galetes" \g \s))

(println)

;; 1.3 ordre-diferents
(defn ordre-diferents [s]
  (->> (str/split s #" ") set sort)
  )

(println "ordre-diferents")
(println "(aa ab bb)-->: " (ordre-diferents "aa bb ab bb ab ab"))

(println)

;; 2.1 ordre-mida
(defn ordre-mida [lst]
  (->> lst (filter #(> (count %) 5)) (sort-by count))
  )

(println "ordre-mida")
(println "(Tòquio Nairobi Barcelona)-->: " (ordre-mida '("Roma" "París" "Tòquio" "Nairobi" "Barcelona")))

(println)
;; 2.2 agrupa
(defn agrupa [lst]
  (letfn [(f_aux [acum ciutat] 
                 (if (< (count (first acum)) 3)
                   (conj (pop acum) (conj (first acum) ciutat))
                   (conj acum (list ciutat))
                    ))]
    (->> lst (reduce f_aux '(())) reverse)
    )
)

(println "agrupa")
(println "((Tòquio París Roma) (Barcelona Nairobi Londres) (Nova Delhi))-->: ")
(println  (agrupa '("Roma" "París" "Tòquio" "Londres" "Nairobi" "Barcelona" "Nova Delhi")))

(println)

;; 2.3 cartesia
(defn cartesia [s1 s2 s3]
  (for [x s1 y s2 z s3] (list x y z))
  )

(println "cartesia")
(println "((z 1 :blanc) (z 1 :negre) (z 4 :blanc) (z 4 :negre) ...)")
(println (cartesia #{"x" "y" "z"} #{1 2 3 4} #{:blanc :negre}))

(println)

;; 3 inner-join
(require '[clojure.set :refer [union]])
(defn inner-join [lst1 lst2 id]
  (for [x lst1 y lst2 :when (= (x id) (y id))] (union x y))
  )


(def regions
  '({:regId 1 :regName "Europe"}
    {:regId 2 :regName "Asia"}))

(def countries
  '({:regId 1 :country "Belgium" :countryID :BE}
    {:regId 1 :country "Switzerland" :countryID :CH}
    {:regId 2 :country "India" :countryID :IN}
    {:regId 2 :country "Japan" :countryID :JP}
    {:regId 1 :country "Denmark" :countryID :DK}))

(println "inner-join")
(def res-join (inner-join regions countries :regId))
(println res-join)

(def gt-join (list {:regId 1, :regName "Europe", :country "Belgium", :countryID :BE}
              {:regId 1, :regName "Europe", :country "Switzerland", :countryID :CH}
              {:regId 1, :regName "Europe", :country "Denmark", :countryID :DK}
              {:regId 2, :regName "Asia", :country "India", :countryID :IN}
              {:regId 2, :regName "Asia", :country "Japan", :countryID :JP}))
(println (= gt-join res-join))
