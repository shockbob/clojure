(ns diamond)

(def alphas "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn spaces [n]
   (apply str (repeat n " ")))

(defn get-level [ch width]
   (if (= ch \A)
        (let [indent (spaces (quot width 2))]
           (str indent \A indent))
       (let [offset (- (int ch) (int \A))
             indent (spaces (- (quot width 2) offset))
             center (spaces (- width (* 2 (count indent)) 2))]
           (str indent ch center ch indent))))

(defn diamond [ch] ;; <- arglist goes here
   (let [level (- (int ch)(int \A))
         width (inc (* level 2))
         top (map (fn [c] (get-level c width)) (take (inc level) alphas))]
     (concat top (rest (reverse top)))))

