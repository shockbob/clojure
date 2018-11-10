(ns go-counting
 (:require [clojure.set :as set]))

(def directions [[-1 0][1 0][0 -1][0 1]])

(defn one-visit [grid [x y] neighbors]
   (if (not= \space (get-in grid [x y]))
       #{[x y]}
       (let [new-neighbors 
                (reduce 
                   (fn [newbies [dx dy]] 
                        (let [[nx ny] [(+ dx x)(+ dy y)]]  
                               (if (get-in grid [nx ny])
                                  (if (contains? neighbors [nx ny]) 
                                       newbies 
                                      (conj newbies [nx ny]))
                                newbies))) 
                   #{} 
                   directions)]
        new-neighbors)))

(defn get-neighbors [grid [x y] neighbors]
   (if (contains? neighbors [x y])
       neighbors
       (let [newbies (one-visit grid [x y] neighbors)]
        (if (empty? newbies)
            neighbors
            (let [neighbors (set/union #{[x y]} neighbors)]
                (reduce (fn [more-neighbors [nx ny]] (let [new-neighbors (get-neighbors grid [nx ny] more-neighbors)]
                                                  (set/union new-neighbors more-neighbors)))
                    neighbors 
                    newbies))))))

(defn colors [grid points]
   (map {\B :black \W :white} (remove #{\space} (set (map (partial get-in grid) points)))))  

(defn flip [stones] (set (map (fn [[x y]] [y x]) stones)))
            
(defn find-spaces [grid neighbors]
  (set (filter (fn [[x y]] (= \space (get-in grid [x y]))) neighbors)))

(defn find-territory [grid [x y]]
   (let [neighbors (get-neighbors grid [x y] #{})
         stones (find-spaces grid neighbors)
         borders (colors grid neighbors)
         owner (if (= 1 (count borders)) (first borders))]
      {:stones (flip stones) :owner owner}))
 

(defn territory [grid [y x]]
    (cond 
       (nil? (get-in grid [x y])) (throw (.Throwable "Out of range"))
       (not= \space (get-in grid [x y]))  {:stones #{} :owner nil}
       :else (find-territory grid [x y]) 
   ))

(defn territory-rc [grid [row col]] (territory grid [col row]))

(defn find-stones [which terr]
     (set (mapcat :stones (filter (fn [mp] (= (mp :owner) which)) terr))))

(defn territories [grid]
     (let [pts (for [ row (range 0 (count grid)) col (range 0 (count (grid 0))) ][row col])
          spaces (find-spaces grid pts)
          terrs (set (map (partial territory-rc grid) spaces))
          white (find-stones :white terrs)
          black (find-stones :black terrs)
          nils (find-stones nil terrs)
          nils (if (and (empty? nils) (empty? black) (empty? white)) spaces nils)
          final {:white-territory white :black-territory  black :null-territory nils}]
    final)) 

