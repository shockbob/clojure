(ns robot-simulator)

(def index-dirs {0 :west 1 :north 2 :east 3 :south}) 
(def dirs-index {:west 0 :north 1 :east 2 :south 3}) 
(def dirs [[-1 0][0 1][1 0][0 -1]])

(defn- turn [bearing add-on]
   (let [index (dirs-index bearing)
         index (mod (+ add-on index) 4)]
        (index-dirs index)))

(defn- advance [coordinates bearing]
   (let [index (dirs-index bearing)
         [dx dy] (dirs index)]
         {:x (+ (coordinates :x) dx) :y (+ (coordinates :y) dy)}))

(defn robot [coordinates bearing] 
    {:coordinates coordinates :bearing bearing})

(defn turn-right [bearing] 
   (turn bearing 1))

(defn turn-left [bearing] 
   (turn bearing 3))

(defn simulate [path robo] 
   (reduce (fn [{bearing :bearing coordinates :coordinates} p] 
                   (cond
                       (= p \A) (robot (advance coordinates bearing) bearing)
                       (= p \L) (robot coordinates (turn-left bearing)) 
                      :else (robot coordinates (turn-right bearing)))) 
           robo
           path))

