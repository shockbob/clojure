(ns pig-latin
 (:require [clojure.string :as str]))

(def vowels #{\a \e \i \o \u})

(defn is-vowel? [c] 
    (contains? vowels c))

(defn translate-word [word] 
    (let [yt-rule (= "yt" (subs word 0 2))
          x-rule (and (= (first word) \x) (not (is-vowel? (second word))))
          starts-with-vowel (is-vowel? (first word))
          const (apply str (take-while (fn [x] (not (is-vowel? x))) word)) 
          const (if (= "qu" (subs word 0 2)) "qu" const) 
          const (if (and (not (is-vowel? (first word))) (= "qu" (subs word 1 3))) (subs word 0 3) const)]
        (cond (or yt-rule x-rule starts-with-vowel) (str word "ay")
              :else (str (apply str (str/replace-first word const "")) const "ay"))))

(defn translate [words] 
    (str/join " " (map translate-word (.split words " "))))
