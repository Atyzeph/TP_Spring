package fr.iocean.species.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMethodeGetLoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// paramètre de 'Before' = PointCut ---> Permet de spécifier quel partie du projet sera concernée par les logs à chaque appel de requête "get"
	@Before("execution(* fr.iocean.species.controller..*(..)))")
	public void logService(JoinPoint jp) {
		// On ajoute aux log la signature de la méthode
		logger.info("Executing controller method {}", jp.getSignature().getName());
		
		
		System.out.println("on entre dans le joinpoint : " + jp);
		// On récupère aussi la valeur de son argument passé en paramètres
		System.out.println(" arguments : " + jp.getArgs());
		System.out.println(" source : " + jp.getSourceLocation());
	}
}
