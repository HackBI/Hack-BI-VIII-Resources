# Introduction to AI and ML

## Artificial Intelligence (AI)

Artificial Intelligence is a branch of computer science focused on creating machines capable of performing tasks that typically require human intelligence. These tasks include reasoning, problem-solving, understanding natural language, and perceiving the environment. AI systems can be classified into two categories: Narrow AI, which is designed to perform specific tasks, and General AI, which would be capable of performing any intellectual task a human can do (though this remains largely theoretical).

## Machine Learning (ML)

Machine Learning is a subset of AI that enables machines to learn from data. Instead of being explicitly programmed to perform a task, ML algorithms analyze data, identify patterns, and make decisions based on what they have learned. This learning process involves feeding vast amounts of data to algorithms, which then build models that can make predictions or classifications.

## Differences Between AI and ML

* AI is the broader concept of machines being able to carry out tasks in a way that we would consider "smart."
* ML is a specific approach to achieving AI, based on the idea that systems can learn from data, identify patterns, and make decisions with minimal human intervention.

## Real World Applications of AI and ML

* **Healthcare:** AI and ML are used in medical imaging to detect diseases, in predictive analytics to forecast patient outcomes, and in personalized medicine to tailor treatments to individual patients.
* **Finance:** Algorithms are used to detect fraudulent activities, automate trading, and provide personalized financial advice.
* **Transportation:** AI is at the core of autonomous vehicles, optimizing routes, and improving traffic management.

# The KNN Algorithm

## Introduction to KNN

K-Nearest Neighbors (KNN) is a simple, yet powerful, supervised machine learning algorithm used for classification and regression tasks. The idea behind KNN is to classify a data point based on how its neighbors are classified. It's a non-parametric method, meaning it does not make any assumptions about the underlying data distribution.

## How KNN works

* **Data Representation:** Each data point is represented in an n-dimensional space, where n is the number of features.
* **Distance Measurement:** To classify a new data point, the algorithm calculates the distance between this point and all other points in the dataset. Common distance metrics include Euclidean distance, Manhattan distance, and Minkowski distance.
* **Finding Neighbors:** Once the distances are calculated, the algorithm identifies the k-nearest neighbors (the k closest data points).
* **Classification:** The new data point is classified by a majority vote of its neighbors. If it's a regression task, the average of the k-nearest neighbors is taken.

## Choosing the Value of K

The value of k is crucial in determining the algorithm's performance. A small value of k (e.g., k=1) might lead to noisy predictions, while a large value of k could smooth out the decision boundaries too much. A common approach is to use cross-validation to find the optimal k.

## Mathematical Theory Behind KNN

* **Euclidean Distance Formula:** For two points `X = (X1, X2, ..., Xn)` and `Y = (Y1,) Y2, ..., Yn)`, the Euclidean distance is calculated as:

$$d(X, Y) = \sqrt{\sum\limits_{i\=1}^{n} (x_i - y_i)^2}$$

* **Majority Voting:** In a classification problem, if k=5 and 3 out of the 5 nearest neighbors belong to class A, then the new data point is classified as class A.

# Implementing KNN with Python

## Setting up the Environment

You will need the following libraries: `numpy`, `pandas`, `matplotlib`, and `sklearn`. Install them using `pip` if you haven't already:

```bash
pip install numpy pandas matplotlib scikit-learn
```

##  Importing Libraries and Dataset

```python
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score

# Example: Loading the Iris dataset
from sklearn.datasets import load_iris
data = load_iris()
X, y = data.data, data.target
```

## Data Preprocessing

Split the data into training and testing sets:

```python
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
```

## Implementing the KNN

Create and train the KNN model 

```python
k = 3  # You can choose different values of k
knn = KNeighborsClassifier(n_neighbors=k)
knn.fit(X_train, y_train)
```

## Making Predictions and Evaluating the Model

```python
y_pred = knn.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy * 100:.2f}%')
```

## Visualizing the Result

For visualization (optional and more applicable to 2D data), you can plot the decision boundaries:

```python
from matplotlib.colors import ListedColormap

# Create a mesh grid
h = .02  # step size in the mesh
x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
xx, yy = np.meshgrid(np.arrange(x_min, x_max, h),
                     np.arrange(y_min, y_max, h))

# Plot the decision boundary by assigning a color to each point in the mesh
Z = knn.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)
plt.figure()
plt.contourf(xx, yy, Z, cmap=plt.cm.RdYlBu)

# Plot also the training points
plt.scatter(X[:, 0], X[:, 1], c=y, edgecolor='k', cmap=plt.cm.RdYlBu)
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.title('KNN Decision Boundary')
plt.show()
```