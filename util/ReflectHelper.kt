//package util
//
//import kotlin.reflect.KClass
//import kotlin.reflect.full.*
//import kotlin.reflect.jvm.jvmName
//
//
//class ReflectHelper<T: Any>(_class:KClass<T>) {
//    val constructors = _class.constructors
//    val members = _class.members
//
//    val isAbstract = _class.isAbstract
//    val isCompanion = _class.isCompanion
//    val isData = _class.isData
//    val isFinal = _class.isFinal
//    val isFun = _class.isFun
//    val isInner = _class.isInner
//    val isOpen = _class.isOpen
//    val isSealed = _class.isSealed
//    val isValue = _class.isValue
//
//    val nestedClasses = _class.nestedClasses
//    val objectInstance = _class.objectInstance
//    val qualifiedName = _class.qualifiedName
//    val sealedSubclasses = _class.sealedSubclasses
//    val simpleName = _class.simpleName
//    val supertypes = _class.supertypes
//    val typeParameters = _class.typeParameters
//    val annotations = _class.annotations
//    val companionObject = _class.companionObject
//    val companionObjectInstance = _class.companionObjectInstance
//    val declaredMemberExtensionProperties = _class.declaredMemberExtensionProperties
//    val declaredMemberProperties = _class.declaredMemberProperties
//    val memberExtensionProperties = _class.memberExtensionProperties
//    val memberProperties = _class.memberProperties
//    val primaryConstructor = _class.primaryConstructor
//
//    val allSuperclasses = _class.allSuperclasses
//    val allSupertypes = _class.allSupertypes
//    val declaredFunctions = _class.declaredFunctions
//    val declaredMemberExtensionFunctions = _class.declaredMemberExtensionFunctions
//    val declaredMemberFunctions = _class.declaredMemberFunctions
//    val declaredMembers = _class.declaredMembers
//    val functions = _class.functions
//    val memberExtensionFunctions = _class.memberExtensionFunctions
//    val memberFunctions = _class.memberFunctions
//    val starProjectedType = _class.starProjectedType
//    val staticFunctions = _class.staticFunctions
//    val staticProperties = _class.staticProperties
//    val superclasses = _class.superclasses
//
//    val jvmName = _class.jvmName
//    val defaultType = _class.defaultType
//
//    private fun <T>printForEach(collectionName: String,collection: Collection<T>){
//        println("$collectionName: ")
//        collection.forEach {
//            println("\t\t$it")
//        }
//    }
//
//    init {
//
//        printForEach("constructors",constructors)
//        printForEach("members",members)
//
//        println("isAbstract: \t$isAbstract")
//        println("isCompanion: \t$isCompanion")
//        println("isData: \t$isData")
//        println("isFinal: \t$isFinal")
//        println("isFun: \t$isFun")
//        println("isInner: \t$isInner")
//        println("isOpen: \t$isOpen")
//        println("isSealed: \t$isSealed")
//        println("isValue: \t$isValue")
//
//        printForEach("nestedClasses",nestedClasses)
//
//        println("objectInstance: \t$objectInstance")
//        println("qualifiedName: \t$qualifiedName")
//        printForEach("sealedSubclasses",sealedSubclasses)
//
//        println("simpleName: \t$simpleName")
//        printForEach("supertypes",supertypes)
//        printForEach("typeParameters",typeParameters)
//        printForEach("annotations",annotations)
//        println("companionObject: \t$companionObject")
//        println("companionObjectInstance: \t$companionObjectInstance")
//        printForEach("declaredMemberExtensionProperties",declaredMemberExtensionProperties)
//        printForEach("declaredMemberProperties",declaredMemberProperties)
//        printForEach("memberExtensionProperties",memberExtensionProperties)
//        printForEach("memberProperties",memberProperties)
//        println("primaryConstructor: \t$primaryConstructor")
//
//        printForEach("allSuperclasses",allSuperclasses)
//        printForEach("allSupertypes",allSupertypes)
//        printForEach("declaredFunctions",declaredFunctions)
//        printForEach("declaredMemberExtensionFunctions",declaredMemberExtensionFunctions)
//        printForEach("declaredMemberFunctions",declaredMemberFunctions)
//        printForEach("declaredMembers",declaredMembers)
//        printForEach("functions",functions)
//        printForEach("memberExtensionFunctions",memberExtensionFunctions)
//        printForEach("memberFunctions",memberFunctions)
//        println("starProjectedType: \t$starProjectedType")
//        printForEach("staticFunctions",staticFunctions)
//        printForEach("staticProperties",staticProperties)
//        printForEach("superclasses",superclasses)
//        println("jvmName: \t$jvmName")
//        println("defaultType: \t$defaultType")
//
//
//
//
//
//    }
//}