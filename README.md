#Intrusion Detection System

###Project Specification:

We want to distinguish malicious users from benign users using the history of bash shell
command history of the users. The idea is to use an algorithm inspired by the eigenface
algorithm for face recognition. The details of the algorithm can be found in the paper
"Anomaly Detection Using Layered Networks Based on Eigen Co-Occurrence Matrix" by
Oka, Oyama, Abe and Kato. The paper is attached with this project specification.
The idea behind the algorithm is to construct connected graphs of bash command flows for
each user. If the flow becomes unusual, then we detect this as a malicious user. The
algorithm works in several stages. Over all sequences in the dataset, we perform Principal
Component Analysis (PCA) to come up with a new coordinate system represented by a set
of eigen co-occurrence matrices. Co-occurrence matrix is analogous to the face in image
analysis while the eigen co-occurrence matrix is analogous to the eigenface. To find the
features associated with a sequence, the algorithm projects the co-occurrence matrix onto
the space defined by the Eigen co-occurrence matrices. In addition, the paper treats each
principal component feature in order, calling each principal component feature “a layer”.
Dataset. We are attaching a dataset obtained from Stanford University as a zipped file.
Inside please find 50 users' command history. Dataset contains UserXX files that contain
15,000 bash commands for User XX. The first 5000 entries in each file are training entries;
they are guaranteed to have been entered by User XX. The rest of the file is test data and is
divided into sections of 100 entries that either belong to user XX or to someone else.
The file reference.txt provides the key for the rest of the file. It is a 100 x 50 matrix. Each
column represents a User index and each row represents whether the segment of 100
commands has been entered by the user (labeled by 0) or by a masquerader (1). For
example, with our counting starting at 1, rather than 0, [row 2, col 3] = 0 means that the
commands 5100 - 5200 in file User3 have actually been entered by User 3.

1) Divide each input into a series of sequences of L = 100. Construct the co-occurrence
matrix for each user (for each sequence). Choose a window size w for your experiments.What are the values for m (total # of commands) and n in the provided dataset? For users 1
and 2 what is the co-occurrence of “rm” with “ls” (ls following rm) for 5 of the 50 sequences
(choose the sequences)?

2) Normalize the matrices to be centered at 0, so that co-occurrences with less than
average frequency would have negative values. What type of mean calculation did you
choose? What are the values for user 1 and 2 for relationship between “rm” and “ls” for
those sequences you chose earlier?

3) Calculate Covariance Matrix by first rearranging the matrices from (b) into a vector.

4) Find the eigenvectors of the covariance matrix and sort them by the eigenvalues.
Plot the contribution rate (equation 8, AnomalyDetection.pdf) to find a good dimension of
the feature space (N). Note that each eigenvector is semantically a matrix converted into a
vector -- so it can be transferred back into a m x m matrix. These matrices is what we call
“Eigen co-occurrence matrices.”

5) Find the feature vectors for users 1-5 and report the feature vectors for users 1 and
2 for their first 5 sequences. Report these in user1FV.csv and user2FV.csv

6) Construct the network layers described in the paper for users 1-5 (for each
sequence) -- either Layered Network Model or Combined Network Model. Specify which
you choose.

7) For each test sequence for users 1-5, evaluate the algorithm by constructing co-
occurrence matrix and mapping it into Eigen matrix space. Then compare the network
similarity to the networks you have associated with that user. Classify each sequence as
anomalous or friendly. Report back the false positive and false negative rates (you can
include more users for extra credit). This section is open to experimentation and you are
free to deviate from the authors’ testing strategies.

8) Classify the test sequences of user 21 as anomalous (1) or benign (0) and submit it
as a user21.csv file with 100 entries (1 classification for each test sequence).
README section. Since this problem is based on a research paper, there are ambiguities
both in algorithm and parameter selection. Please note the assumptions that you made,
which parameters you chose, and how you tweaked them to come up with the best result. In
the same README section, specify what you thought was the hardest part of this problem.
Also, if you are working in a group, specify how you divided up the work. It’s ok to not reach
the accuracy results in the paper.

