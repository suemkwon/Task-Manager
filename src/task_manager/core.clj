(ns task-manager.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.data.json :as json]))

;; In-memory storage for tasks
(def tasks (atom {}))
(def task-id-counter (atom 0))

;; Task CRUD operations
(defn create-task [task]
  (let [id (swap! task-id-counter inc)
        new-task (assoc task :id id)]
    (swap! tasks assoc id new-task)
    new-task))

(defn update-task [id task]
  (when (@tasks id)
    (let [updated-task (assoc task :id id)]
      (swap! tasks assoc id updated-task)
      updated-task)))

(defn delete-task [id]
  (when (@tasks id)
    (swap! tasks dissoc id)
    {:message (str "Task " id " deleted")}))

;; Routes definition
(defroutes app-routes
  ;; Get all tasks
  (GET "/tasks" []
    (response (vals @tasks)))
  
  ;; Get task by id
  (GET "/tasks/:id" [id]
    (if-let [task (@tasks (Integer/parseInt id))]
      (response task)
      {:status 404
       :body {:error "Task not found"}}))
  
  ;; Create new task
  (POST "/tasks" {body :body}
    (response (create-task body)))
  
  ;; Update task
  (PUT "/tasks/:id" {{:keys [id]} :params body :body}
    (if-let [updated-task (update-task (Integer/parseInt id) body)]
      (response updated-task)
      {:status 404
       :body {:error "Task not found"}}))
  
  ;; Delete task
  (DELETE "/tasks/:id" [id]
    (if-let [result (delete-task (Integer/parseInt id))]
      (response result)
      {:status 404
       :body {:error "Task not found"}}))
  
  ;; Default route for handling 404s
  (route/not-found {:error "Not Found"}))

;; Application definition with middleware
(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})
      wrap-json-response
      (wrap-defaults api-defaults)))

;; Main function to start the server
(defn -main []
  (println "Starting server on port 3000...")
  (run-jetty app {:port 3000 :join? false})
  (println "Server running on http://localhost:3000"))