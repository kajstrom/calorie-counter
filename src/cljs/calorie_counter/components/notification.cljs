(ns calorie-counter.components.notification
  (:require [reagent.core :as r]))

(defonce notifications (r/atom []))
(def notification-duration-ms 5000)

(defn remove-notification [id]
  (swap! notifications (fn [s] (remove (fn [notification] (= (:id notification) id)) s))))

(defn add-notification [msg type]
  "Adds a notification message with type. Possible types are bootstrap alert types without the 'alert-' prefix"
  (let [id (random-uuid)]
    (->> (hash-map :msg msg :type (str "alert-" (name type)) :id id)
         (swap! notifications conj))
    (js/setTimeout #(remove-notification id) notification-duration-ms)))

(defn notification-list []
  [:div (for [notification @notifications]
          ^{:key (:id notification)} [:div.alert {:class (:type notification)} (:msg notification)])])