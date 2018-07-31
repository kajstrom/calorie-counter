(ns calorie-counter.components.auth
  (:require [reagent.core :as r]))

(defn registration-form []
  (let [fields (r/atom {})]
    [:div
     [:div.form-group
      [:label "Email"]
      [:input.form-control {:name :email
                            :placeholder "someone@example.org"
                            :on-change #(swap! fields assoc :email (-> % .-target .-value))}]]
     [:div.form-group
      [:label "Password"]
      [:input.form-control {:name :password
                            :type :password
                            :on-change #(swap! fields assoc :password (-> % .-target .-value))}]]
     [:div.form-group
      [:label "First name"]
      [:input.form-control {:name :first_name
                            :on-change #(swap! fields assoc :first_name (-> % .-target .-value))}]]
     [:div.form-group
      [:label "Last name"]
      [:input.form-control {:name :last_name
                            :on-change #(swap! fields assoc :last_name (-> % .-target .-value))}]]
     [:button.btn.btn-primary {:on-click #(println @fields)} "Submit"]]))