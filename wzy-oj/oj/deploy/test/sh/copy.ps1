rm D:/IDE/code/wz-oj/deploy/test/bitoj-jar/gateway/oj-gateway.jar
rm D:/IDE/code/wz-oj/deploy/test/bitoj-jar/friend/oj-friend.jar
rm D:/IDE/code/wz-oj/deploy/test/bitoj-jar/job/oj-job.jar
rm D:/IDE/code/wz-oj/deploy/test/bitoj-jar/judge/oj-judge.jar
rm D:/IDE/code/wz-oj/deploy/test/bitoj-jar/system/oj-system.jar

copy D:/IDE/code/wz-oj/oj-gateway/target/oj-gateway-1.0-SNAPSHOT.jar D:/IDE/code/wz-oj/deploy/test/bitoj-jar/gateway/oj-gateway.jar
copy D:/IDE/code/wz-oj/oj-modules/oj-judge/target/oj-judge-1.0-SNAPSHOT.jar D:/IDE/code/wz-oj/deploy/test/bitoj-jar/judge/oj-judge.jar
copy D:/IDE/code/wz-oj/oj-modules/oj-friend/target/oj-friend-1.0-SNAPSHOT.jar D:/IDE/code/wz-oj/deploy/test/bitoj-jar/friend/oj-friend.jar
copy D:/IDE/code/wz-oj/oj-modules/oj-job/target/oj-job-1.0-SNAPSHOT.jar D:/IDE/code/wz-oj/deploy/test/bitoj-jar/job/oj-job.jar
copy D:/IDE/code/wz-oj/oj-modules/oj-system/target/oj-system-1.0-SNAPSHOT.jar D:/IDE/code/wz-oj/deploy/test/bitoj-jar/system/oj-system.jar
pause