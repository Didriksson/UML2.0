package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ConstantsAndEnums.Constants;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;
import UML.Components.UMLVariable;

public class RelationTest {
	static String path = ".\\src\\GeneratedTestFiles\\";
	UMLComponent umlClass1;
	UMLComponent umlClass2;

	@Before
	public void setUp() {
		UMLMethod method;
		UMLVariable v;

		v = new UMLVariable("int", "numberOfboats");
		method = new UMLMethod("public", "void", "print");
		method.addVariable(v);
		umlClass1 = new UMLComponent("TestKlass", "class");
		umlClass2 = new UMLComponent("KlassTest", "class");

		try {
			umlClass1.addMethod(method);
			umlClass2.addMethod(new UMLMethod("public", "void", "testMEthod"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void relationBetweenClassTest() {
		UMLRelation relation = new UMLRelation(Constants.ASSOCIATION_STRING);
		relation.setRoot(umlClass1);
		relation.setDestination(umlClass2);
		try {
			relation.setMultiplicityDestination("1");
			relation.setMultiplicityRoot("2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String shouldBe = "Relation type: " + Constants.ASSOCIATION_STRING
				+ "\n" + "Root element: " + umlClass1.getName() + "\n"
				+ "Destination element: " + umlClass2.getName() + "\n";
		assertEquals(shouldBe, relation.toString());
		assertEquals("2", relation.getMultiplicityRoot());
		assertEquals("1", relation.getMultiplicityDestination());

	}

}
