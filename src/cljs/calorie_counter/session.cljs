(ns calorie-counter.session
  (:require [reagent.core :as r]))

(defonce session (r/atom {:page :home}))

(defn redirect-to-hash! [path]
  "Redirect browser to a new path in the front-end application"
  (set! (.-hash js/window.location) path))