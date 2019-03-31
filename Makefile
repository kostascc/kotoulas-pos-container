build:
	cd zazu-softpos && ./compile.sh && cd ../
	docker build --no-cache -t kotoulas-pos-container .
 
run:
	docker run -d -p 4080:80 --name kotoulas-pos-container ubuntu

clean:
	docker stop kotoulas-pos-container && docker rm kotoulas-pos-container -v