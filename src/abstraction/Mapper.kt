package top.jotyy.abstraction

interface Mapper<in T, out R: IEntity> {

    fun convert(t: T): R
}
