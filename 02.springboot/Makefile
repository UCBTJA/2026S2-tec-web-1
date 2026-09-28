PORT := 8090
LOG_FILE := /tmp/demo-bootRun.log

.PHONY: stop start build test clean

stop:
	@PID=$$(lsof -ti tcp:$(PORT) 2>/dev/null); \
	if [ -n "$$PID" ]; then \
		echo "Deteniendo proceso en el puerto $(PORT) (PID $$PID)..."; \
		kill $$PID; \
		sleep 1; \
		if kill -0 $$PID 2>/dev/null; then kill -9 $$PID; fi; \
		echo "Detenido."; \
	else \
		echo "Puerto $(PORT) libre."; \
	fi

start: stop
	@echo "Arrancando la aplicación (log: $(LOG_FILE))..."
	@nohup ./gradlew bootRun > $(LOG_FILE) 2>&1 & disown
	@echo "Lanzado en segundo plano. Revisa $(LOG_FILE) para ver el estado."

build:
	./gradlew build

test:
	./gradlew test

clean:
	./gradlew clean
