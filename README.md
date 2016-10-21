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

